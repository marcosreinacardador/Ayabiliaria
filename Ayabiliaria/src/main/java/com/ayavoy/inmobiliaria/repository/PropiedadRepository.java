package com.ayavoy.inmobiliaria.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ayavoy.inmobiliaria.repository.entity.Propiedad;

public interface PropiedadRepository extends PagingAndSortingRepository<Propiedad, Long>{

//	Haced un servicio web que devuelva todos las propiedades de la base de datos Ayabiliaria :)
//	http://localhost:8081/ayabiliaria/listarPropiedades
	@Query(value = "Select * from propiedad;", nativeQuery = true)
	Iterable<Propiedad> listaPropiedades();
}
