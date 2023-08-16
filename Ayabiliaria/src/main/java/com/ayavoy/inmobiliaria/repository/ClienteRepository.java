package com.ayavoy.inmobiliaria.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String>{

//	Haced un servicio web que devuelva todos los clientes de la base de datos cliente :)
//	http://localhost:8081/ayabiliaria/ListarClientes
	@Query(value = "Select * from cliente;", nativeQuery = true)
	Iterable<Cliente> listaClientes();
	
}
