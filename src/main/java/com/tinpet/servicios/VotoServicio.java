package com.tinpet.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tinpet.entidades.Mascota;
import com.tinpet.entidades.Voto;
import com.tinpet.repositorios.MascotaRepo;
import com.tinpet.repositorios.VotoRepo;

public class VotoServicio {

	@Autowired
	private MascotaRepo mascotaR;
	@Autowired
	private VotoRepo votoR;
	
	@Autowired
	private MailServicio mailS;
	
	public void votar(int idUsuario, int idMascota, int idMascota2) throws Exception {
		if(idMascota == idMascota2) {
			throw new Exception("No se pueden realizar votos propios");
		}
		
		Voto voto = new Voto();
		
		voto.setFecha(new Date());
		Optional<Mascota> respuesta = mascotaR.findById(idMascota);
		if(respuesta.isPresent()) {
			Mascota mascota1 = respuesta.get();
			if(mascota1.getUsuario().getId() == idUsuario) {
				voto.setM1(mascota1);
			}else {
				throw new Exception("No tiene los permisos para realizar el voto");
			}
		}else {
			throw new Exception("No existe las mascosta en la besa de datos");
		}
		
		Optional<Mascota> respuesta2 = mascotaR.findById(idMascota2);
		if(respuesta2.isPresent()) {
			Mascota mascota2 = respuesta2.get();
			voto.setM2(mascota2);
			
			mailS.Enviar("Tu mascota ha sido votada.", "TINPET", mascota2.getUsuario().getMail());
		}else {
			throw new Exception("La mascota no existe en la base de datos");
		}
		
		votoR.save(voto);
	}
	
	//metodo que responde el voto 
	public void respuestaVoto(int idVoto) throws Exception {
		Optional<Voto> respuesta = votoR.findById(idVoto);
		if(respuesta.isPresent()) {
			Voto voto = respuesta.get();
			voto.setRespuesta(new Date());
			
			votoR.save(voto);
			
			mailS.Enviar("Tu mascota ha sido matcheada.", "TINPET", voto.getM1().getUsuario().getMail());
		}else {
			throw new Exception("El voto no existe");
		}
		
	}
}
