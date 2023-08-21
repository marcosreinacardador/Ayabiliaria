package com.ayavoy.inmobiliaria.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayavoy.inmobiliaria.repository.entity.Propiedad;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "propiedad", schema = "bdayabiliaria")
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroReferencia; // Índice

    @ManyToOne
    @JoinColumn(name = "dni_cliente")
    private Cliente cliente;

    private String tipoPropiedad;
    private String operacion;
    private String direccion;
    private String localidad;
    private String poblacion;
    private String codigoPostal;
    private int total;
    private String descripcion;
    @Column(name = "creado_en")
	private LocalDateTime creadoEN;
    @Lob   // este atributo es un fichero tipo blob
	@JsonIgnore  // no queremos que este atributo vaya con el JSON de respuesta, para que no lo serialize
	private byte[] foto;
    
	public long getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getTipoPropiedad() {
		return tipoPropiedad;
	}
	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public LocalDateTime getCreadoEN() {
		return creadoEN;
	}
	public void setCreadoEN(LocalDateTime creadoEN) {
		this.creadoEN = creadoEN;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	@PrePersist // este metodo, marcado así, se ejecuta antes de insertar el restaurante
	private void generarFechaCreacion () 
	{
		this.creadoEN = LocalDateTime.now();  // obtengo la fecha de hoy metodo estatico 
	}
	
	public Integer getFotoHashCode() {  // genera una bandera o flag
		
		Integer idev = null;
		
			if(this.foto!= null) {
				idev = this.foto.hashCode();  //hascode es la direccion de memoria del atributo idev
			}
		
		return idev;
	}

	
	/**
	 * @param numeroReferencia
	 * @param string
	 * @param tipoPropiedad
	 * @param operacion
	 * @param direccion
	 * @param localidad
	 * @param poblacion
	 * @param codigoPostal
	 * @param total
	 * @param descripcion
	 */
	public Propiedad(long numeroReferencia, Cliente cliente, String tipoPropiedad, String operacion,
			String direccion, String localidad, String poblacion, String codigoPostal, int total,
			String descripcion, LocalDateTime creadoEN) {
		super();
		this.numeroReferencia = numeroReferencia;
		this.cliente = cliente;
		this.tipoPropiedad = tipoPropiedad;
		this.operacion = operacion;
		this.direccion = direccion;
		this.localidad = localidad;
		this.poblacion = poblacion;
		this.codigoPostal = codigoPostal;
		this.total = total;
		this.descripcion = descripcion;
		this.creadoEN = creadoEN;
	}
	
	public Propiedad() {
		
	}
	
	public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
	}
	
	
	
}


