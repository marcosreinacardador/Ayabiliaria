package com.ayavoy.inmobiliaria.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;

@Entity
@Table(name = "cliente", schema = "bdayabiliaria")
public class Cliente {
	@Id
    private String dni; // Índice

    private String nombre;
    private String apellidos;
    private String telefonoContacto;
    private String correoElectronico;
    private String datosBancarios;
    @Column(name = "creado_en")
	private LocalDateTime creadoEN;
    
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getDatosBancarios() {
		return datosBancarios;
	}
	public void setDatosBancarios(String datosBancarios) {
		this.datosBancarios = datosBancarios;
	}
	
	public LocalDateTime getCreadoEN() {
		return creadoEN;
	}
	
	public void setCreadoEN(LocalDateTime creadoEN) {
		this.creadoEN = creadoEN;
	}
	
	@PrePersist // este metodo, marcado así, se ejecuta antes de insertar el restaurante
	private void generarFechaCreacion () 
	{
		this.creadoEN = LocalDateTime.now();  // obtengo la fecha de hoy metodo estatico 
	}
	
	
	
	
	public Cliente() {
		
		// TODO Auto-generated constructor stub
	}

	


	public Cliente(String dni, String nombre, String apellidos, String telefonoContacto, String correoElectronico,
			String datosBancarios, LocalDateTime creadoEN) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefonoContacto = telefonoContacto;
		this.correoElectronico = correoElectronico;
		this.datosBancarios = datosBancarios;
		this.creadoEN = creadoEN;
	}




	public interface ClienteRepository extends JpaRepository<Cliente, String> {
		
	}

	
	
	
}
