package com.ayavoy.inmobiliaria.service;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;
import com.ayavoy.inmobiliaria.repository.entity.Propiedad;

public interface AyabiliariaService {
	
	// no recibo nada, que devuelvo un iterable de ayabiliria, dará error en
	// AyabiliariaServiceImpl, habrá que add este iterable
	// Iterable lo que hace es recorrer
	Iterable<Cliente> consultarTodosClientes();
	Iterable<Propiedad> consultarTodasPropiedades();
	
	// damos de alta un nuevo cliente y nos devuelve el cliente ademas con la fecha
	Cliente altaClienteService(Cliente cliente);
	
	// damos de alta una nueva propiedad y nos devuelve con la foto y la fecha, además tiene que existir el cliente
	Propiedad altaPropiedadService(Propiedad propiedad);
}
