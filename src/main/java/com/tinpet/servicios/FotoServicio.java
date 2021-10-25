package com.tinpet.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tinpet.entidades.Foto;
import com.tinpet.repositorios.FotoRepo;

@Service
public class FotoServicio {
	@Autowired
	private FotoRepo fotoR;

	@Transactional
	public Foto guardarFoto(MultipartFile archivo) { //multipartfile es en donde se almacena la foto
		if(archivo != null) {
			try {
				Foto foto = new Foto();
				foto.setNombre(archivo.getName());
				foto.setMime(archivo.getContentType());
				foto.setContenido(archivo.getBytes());
				
				return fotoR.save(foto);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
	
	@Transactional
	public Foto actualizarFoto(MultipartFile archivo, int idFoto) {
		if(archivo != null) {
			try {
				Foto foto = new Foto();
				foto = fotoR.findById(idFoto).get();
				foto.setNombre(archivo.getName());
				foto.setMime(archivo.getContentType());
				foto.setContenido(archivo.getBytes());
				
				return fotoR.save(foto);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}
