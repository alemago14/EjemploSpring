package com.tinpet.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Foto {

	//atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String mime; //mime asigna el tipo de archivo de la imagen
	
	@Lob			//le indicamos a la persistencia que es un atributo muy pesado con mucho contenido
	@Basic(fetch = FetchType.LAZY)  //indica que este contenido se cargue solo cuando lo solicetemos agilizando la querys
	private byte[] contenido;

	//constructores
	public Foto() {
		super();
	}

	public Foto(int id, String nombre, String mime, byte[] contenido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mime = mime;
		this.contenido = contenido;
	}

	//setters y getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	
}
