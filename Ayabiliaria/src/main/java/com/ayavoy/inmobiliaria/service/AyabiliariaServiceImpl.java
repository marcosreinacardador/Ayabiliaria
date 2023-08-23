package com.ayavoy.inmobiliaria.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayavoy.inmobiliaria.repository.ClienteRepository;
import com.ayavoy.inmobiliaria.repository.PropiedadRepository;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;
import com.ayavoy.inmobiliaria.repository.entity.Propiedad;


@Service
public class AyabiliariaServiceImpl implements AyabiliariaService {

	@Autowired   //hace falta inyectar
	ClienteRepository clienteRepository;   //instancia de la base de datos

	@Autowired  //have falta inyectar
	PropiedadRepository propiedadRepository;   //instancia de la base de datos

	// Listar todos los clientes dados de alta en Ayabiliaria.
	@Override
	//utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Clientes
	@Transactional(readOnly = true)
	public Iterable<Cliente> consultarTodosClientes() {
		return this.clienteRepository.findAll();
	}

	// Listar todas las propiedades incluidos los clientes que es de cada una
	@Override
	//utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Propiedad
	@Transactional(readOnly = true)
	public Iterable<Propiedad> consultarTodasPropiedades() {
		return this.propiedadRepository.findAll();
	}
	
	// Dar de alta a un cliente nuevo
	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public Cliente altaClienteService(Cliente cliente) {
		return this.clienteRepository.save(cliente);
		//return null;
	}
	
	// Dar de alta a una propiedad nueva
	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public Propiedad altaPropiedadService(Propiedad propiedad) {
		return this.propiedadRepository.save(propiedad);
		//return null;
	}
	
	@Override
	@Transactional
	public Cliente modificarClienteService(String dni, Cliente clienteModificado) {
        Cliente clienteExistente = clienteRepository.findByDni(dni);

        if (clienteExistente != null) {
            // Actualiza los atributos del cliente existente con los valores del clienteModificado
            clienteExistente.setNombre(clienteModificado.getNombre());
            clienteExistente.setApellidos(clienteModificado.getApellidos());
            clienteExistente.setTelefonoContacto(clienteModificado.getTelefonoContacto());
            clienteExistente.setCorreoElectronico(clienteModificado.getCorreoElectronico());
            clienteExistente.setDatosBancarios(clienteModificado.getDatosBancarios());
            // ... actualiza otros atributos según sea necesario

            return clienteRepository.save(clienteExistente);
        }

	        return null; // Manejar caso en el que el cliente no existe
	}
	
/*
	public Optional<Propiedad> modificarPropiedadService(Long numeroReferencia, Propiedad propiedadModificada) {
		Optional<Propiedad> propiedadExistente = propiedadRepository.findById(numeroReferencia);

		if (propiedadExistente != null) {
		    // Actualiza los atributos del cliente existente con los valores del clienteModificado
			propiedadExistente.setCliente(propiedadModificada.getCliente());
			propiedadExistente.setTipoPropiedad(propiedadModificada.getTipoPropiedad());
			propiedadExistente.setOperacion(propiedadModificada.getOperacion());
			propiedadExistente.setDireccion(propiedadModificada.getDireccion());
			propiedadExistente.setLocalidad(propiedadModificada.getLocalidad());
			propiedadExistente.setPoblacion(propiedadModificada.getPoblacion());
			propiedadExistente.setCodigoPostal(propiedadModificada.getCodigoPostal());
			propiedadExistente.setTotal(propiedadModificada.getTotal());
			propiedadExistente.setDescripcion(propiedadModificada.getDescripcion());
			propiedadExistente.setFoto(propiedadModificada.getFoto());
		  
		
		    return propiedadRepository.save(propiedadExistente);
		}
			System.out.println("Propiedad con Número referencia: " + numeroReferencia + " no existe.");
		    return null; // Manejar caso en el que el cliente no existe
		}
*/
	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public Optional<Propiedad> modificaPropiedadService(Long numeroReferencia, Propiedad propiedad) {
		Optional<Propiedad> opProp = Optional.empty();
		// 1 LEER
		opProp =  this.propiedadRepository.findById(numeroReferencia);
			if(opProp.isPresent()) {  // lo has encontrado si?
				// Al estar dentro de una transaccion, propiedadLeida está asociado a un registro de la tabla.
				// Si modifico un campo, estoy modificando la columna asociada (Estado "Persistent" - JPA)
 				Propiedad propiedadLeida  = opProp.get();
 				
 				// Guardar el valor actual de creadoEn sino hago esto me mete null al actualizarlo
 				LocalDateTime creadoEnOriginal = propiedadLeida.getCreadoEN();

 				//restauranteLeido.setNombre(restaurante.getNombre()); en vez de hacerlo uno a uno por campo lo hacemos con BeanUtils.copyProperties
 				BeanUtils.copyProperties(propiedad, propiedadLeida, "NumeroReferencia", "creadoEN", "cliente");   // copiame todos los atributos de propiedadLeia a propiedad, menos numeroReferencia y creadoEn
 				
 				// Restaurar el valor original de creadoEn
 				propiedadLeida.setCreadoEN(creadoEnOriginal);

 				opProp = Optional.of(propiedadRepository.save(propiedadLeida)); //Rellenamos el Optional
			}
		// 2 ACTUALIZAR
		return opProp;
	}

	
	}



