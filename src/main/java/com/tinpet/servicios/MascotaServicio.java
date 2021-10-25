package com.tinpet.servicios;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.tinpet.entidades.Foto;
import com.tinpet.entidades.Mascota;
import com.tinpet.entidades.Usuario;
import com.tinpet.repositorios.MascotaRepo;
import com.tinpet.repositorios.UsuarioRepo;

public class MascotaServicio {

	@Autowired
	private UsuarioRepo usuarioR;
	@Autowired
	private MascotaRepo mascotaR;
	@Autowired
	private FotoServicio fotoS;
	
	@Transactional
	public void crearMascota(MultipartFile archivo, int id, String nombre) throws Exception {
		Usuario user = usuarioR.findById(id).get();
		
		validar(nombre);
		
		Mascota mascota = new Mascota();
		mascota.setNombre(nombre);
		mascota.setAlta(new Date());
		Foto foto = fotoS.guardarFoto(archivo);
		mascota.setFoto(foto);
		mascotaR.save(mascota);
	}
	
	public void validar(String nombre) throws Exception {
		if(nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre de la mascota esta vacio");
		}
	}
	
	@Transactional
	public void modificar(MultipartFile archivo, int idUsuario, int idMascota, String nombre) throws Exception {
		validar(nombre);
		
		Optional<Mascota> respuesta = mascotaR.findById(idMascota);
		if(respuesta.isPresent()) {
			Mascota mascota = respuesta.get();
			if(mascota.getUsuario().getId() == idUsuario) {
				mascota.setNombre(nombre);
				
				int idFoto;
				Foto foto;
				if(mascota.getFoto() != null) {
					idFoto = mascota.getFoto().getId();
					foto = fotoS.actualizarFoto(archivo, idFoto);
					mascota.setFoto(foto);
				}else {
					foto = fotoS.guardarFoto(archivo);
					mascota.setFoto(foto);
				}
				mascotaR.save(mascota);
			}else {
				throw new Exception("El usuario no corresponde");
			}
		}else {
			throw new Exception("La mascota no exite en la base de datos");
		}
		
	}
	
	public void eliminar(int idUsuario, int idMascota) throws Exception {
		Optional<Mascota> respuesta = mascotaR.findById(idMascota);
		if(respuesta.isPresent()) {
			Mascota mascota = respuesta.get();
			if(mascota.getUsuario().getId() == idUsuario) {
				mascota.setBaja(new Date());
				
				mascotaR.save(mascota);
			}else {
				throw new Exception("El usuario no corresponde");
			}
		}else {
			throw new Exception("La mascota no exite en la base de datos");
		}
	}
}
