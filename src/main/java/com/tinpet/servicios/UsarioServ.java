package com.tinpet.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tinpet.entidades.Foto;
import com.tinpet.entidades.Usuario;
import com.tinpet.repositorios.FotoRepo;
import com.tinpet.repositorios.UsuarioRepo;

@Service
public class UsarioServ implements UserDetailsService{

	@Autowired
	private UsuarioRepo usuarioR;
	
	@Autowired
	private MailServicio mailS;
	
	
	@Autowired
	private FotoServicio fotoS;
	
	@Transactional
	public void registrar(MultipartFile archivo, String nombre, String apellido, String mail, String clave) throws Exception {
		validar(nombre, apellido, mail, clave);
		
		Usuario user = new Usuario();
		user.setNombre(nombre);
		user.setApellido(apellido);
		user.setMail(mail);
		String encriptada = new BCryptPasswordEncoder().encode(clave);
		user.setClave(encriptada);
		
		Foto foto = fotoS.guardarFoto(archivo);
		user.setFoto(foto);
		
		usuarioR.save(user);
		
		mailS.Enviar("Bienvenido a Tinpet","TINPET",user.getMail());
	}
	
	@Transactional
	public void modificar(MultipartFile archivo, int id, String nombre, String apellido, String mail, String clave) throws Exception {
		Optional<Usuario> usera = usuarioR.findById(id);
		
		if(usera.isPresent()) {
			Usuario user = usera.get();
			validar(nombre, apellido, mail, clave);
			
			user.setNombre(nombre);
			user.setApellido(apellido);
			user.setMail(mail);
			int idFoto;
			Foto foto;
			if(user.getFoto() != null) {
				idFoto = user.getFoto().getId();
				foto = fotoS.actualizarFoto(archivo, idFoto);
				user.setFoto(foto);
			}else {
				foto = fotoS.guardarFoto(archivo);
				user.setFoto(foto);
			}
			
			usuarioR.save(user);
		}else {
			throw new Exception("No se encuentra en la base de datos");
		}
		validar(nombre, apellido, mail, clave);
		
		
	}
	
	public void validar(String nombre, String apellido, String mail, String clave) throws Exception {
		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre esta vacio");
		}
		
		if (apellido == null || apellido.isEmpty()) {
			throw new Exception("El apellido esta vacio");
		}
		
		if(mail == null || mail.isEmpty()) {
			throw new Exception("El mail esta vacio");
		}
		
		if(clave == null || clave.isEmpty() || clave.length() < 6) {
			throw new Exception("No ingreso la clave o la clave tiene menos de 6 caracteres");
		}
	}
	
	@Transactional
	public void deshabilitar(int id) throws Exception {
Optional<Usuario> usera = usuarioR.findById(id);
		
		if(usera.isPresent()) {
			Usuario user = usera.get();
			
			user.setBaja(new Date());
			
			usuarioR.save(user);
		}else {
			throw new Exception("No se encuentra en la base de datos");
		}
	}
	
	@Transactional
	public void habilitar(int id) throws Exception {
		Optional<Usuario> usera = usuarioR.findById(id);
				
				if(usera.isPresent()) {
					Usuario user = usera.get();
					
					user.setBaja(null);
					
					usuarioR.save(user);
				}else {
					throw new Exception("No se encuentra en la base de datos");
				}
			}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioR.buscarPorMail(mail);
		if(usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList();
			GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
			GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_MASCOTAS");
			GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_VOTOS");
			permisos.add(p1);
			permisos.add(p2);
			permisos.add(p3);
			
			User user = new User(usuario.getMail(), usuario.getClave(), permisos);
			return user;
		}else {
			return null;
		}
		
	}
}
