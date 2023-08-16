package com.ayavoy.inmobiliaria.service;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;
import com.ayavoy.inmobiliaria.repository.entity.Propiedad;

public interface AyabiliariaService {
	
	// no recibo nada, que devuelvo un iterable de ayabiliria, dará error en
	// AyabiliariaServiceImpl, habrá que add este iterable
	// Iterable lo que hace es recorrer
	Iterable<Cliente> consultarTodosClientes();
	Iterable<Propiedad> consultarTodasPropiedades();
	
}
