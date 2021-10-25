package com.tinpet.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	//atributos
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	private String clave;
	@ManyToOne      ///relacion a zona
	private Zona zona;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date alta;
	@Temporal(TemporalType.TIMESTAMP)
	private Date baja;
	@OneToOne
	private Foto foto;
	//getters y setters
	
	
	public int getId() {
		return id;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getMail() {
		return mail;
	}
	public String getClave() {
		return clave;
	}
	public Date getAlta() {
		return alta;
	}
	public Date getBaja() {
		return baja;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public void setBaja(Date baja) {
		this.baja = baja;
	}
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	
	
}
