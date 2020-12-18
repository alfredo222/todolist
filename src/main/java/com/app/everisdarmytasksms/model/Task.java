package com.app.everisdarmytasksms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class Task {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Generación automática del id
	private int id;
	
	@Column
	@Size(min = 1, max = 250, message="La descripción debe tener entre 1 y 250 caracteres")
	private String descripcion;
	
	@Column
	private String estado;
	
	//Default Constructor
	public Task() {}
	
	//Constructor
	public Task(String descripcion, String estado) {
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	//Metodos Get
	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado() {
		return estado;
	}
	
	//Metodos Set

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
