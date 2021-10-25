package com.tinpet.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Voto {

	//atributos
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date respuesta;
	@ManyToOne
	private Mascota m1;
	@ManyToOne
	private Mascota m2;
	
	//getters y setters
	public int getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public Date getRespuesta() {
		return respuesta;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setRespuesta(Date respuesta) {
		this.respuesta = respuesta;
	}
	public Mascota getM1() {
		return m1;
	}
	public void setM1(Mascota m1) {
		this.m1 = m1;
	}
	public Mascota getM2() {
		return m2;
	}
	public void setM2(Mascota m2) {
		this.m2 = m2;
	}
	
	
}
