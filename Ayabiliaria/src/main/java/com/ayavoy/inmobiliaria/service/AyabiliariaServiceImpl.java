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

	@Override
	//utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Clientes
	@Transactional(readOnly = true)
	public Iterable<Cliente> consultarTodosClientes() {
		Iterable<Cliente> lista_clientes = null;
		//le pido todos los clientes a la base de datos cliente
		//y le devuelvo todos los clientes con iterable findAll()
		lista_clientes = this.clienteRepository.listaClientes();
		return lista_clientes;
	}

	@Override
	//utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Propiedad
	@Transactional(readOnly = true)
	public Iterable<Propiedad> consultarTodasPropiedades() {
		// TODO Auto-generated method stub
		return null;
	}
}
