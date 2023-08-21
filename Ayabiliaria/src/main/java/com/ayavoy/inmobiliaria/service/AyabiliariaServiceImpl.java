package com.ayavoy.inmobiliaria.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
