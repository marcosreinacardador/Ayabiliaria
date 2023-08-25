package com.ayavoy.inmobiliaria.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ayavoy.inmobiliaria.repository.entity.Cliente;
import com.ayavoy.inmobiliaria.repository.entity.Cliente.ClienteRepository;
import com.ayavoy.inmobiliaria.repository.entity.Propiedad;

import com.ayavoy.inmobiliaria.service.AyabiliariaService;
/**
 * 
 * API WEB HTTP -> Deriva en la ejecución de un método
 * 
 * GET -> consultar todas las propiedades
 * GET -> consultar todos los propietarios
 * 
 * 
 * POST -> insertar un propietario nuevo
 * POST -> insertar una propiedad nueva
 * PUT  -> modificación ¿?
 * PUT  -> cambia estado ¿?
 * GET 	-> Búsqueda -> ¿?
 */


//@Controller    // Devuelve una vista(html/jsp)
@CrossOrigin(originPatterns = { "*" }, 
	methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }) // le digo que cualquier origen cruzado va a contestar
@RestController // Devolvemos un JSON http://localhost:8081/ayabiliaria
@RequestMapping("/ayabiliaria")
public class AyabiliariaController {

	@Autowired
	AyabiliariaService ayabiliariaService;  //viene llamado desde AyabiliariaServiceImple
	
	 
	@Autowired
	Environment environment; // de aqui voy asacar la instancia del puerto
	
	Logger logger = LoggerFactory.getLogger(AyabiliariaController.class);
	
	// hacemos un test de obtener datos de un cliente
	@GetMapping("/test1") // http://localhost:8081/ayabiliaria/test1
	public Cliente obtenerClienteTest() {
	
		Cliente cliente = null;
		
		System.out.println("Llamando a obtenerClienteTest");
		logger.debug("estoy en obtenerClienteTest");
		
		cliente = new Cliente("30744852X", 
							  "Antonio", 
							  "Deza Ramirez", 
							  "682052124", 
							  "antodeza@gmail.com", 
							  "ES20 2500 1100 1204 1204", 
							  LocalDateTime.now());
		return cliente;
		
	}
	// hacemos un test para mostrar datos de una propiedad
	@GetMapping("/test2") //http://localhost:8081/ayabiliaria/test2
	public ResponseEntity<Propiedad> obtenerPropiedadTest() {
		
		Cliente cliente = new Cliente();
		cliente.setDni("34567890X"); // Establece otros atributos de Cliente si es necesario
		
		System.out.println("Llamando a obtenerPropiedadTest");
		logger.debug("estoy en obtenerPropiedadTest");
		Propiedad propiedad = new Propiedad(2023,
								  cliente,
								  "Piso", 
								  "Venta", 
								  0,
								  "C/ Capricho, 15", 
								  "Fuengirola", 
								  "Málaga", 
								  "29651", 
								  120000, 
								  "Vivenda en buen estado. 100m2, 2 dormitorios, cocina, cuarto de baño",
								  LocalDateTime.now());
		
		return new ResponseEntity<>(propiedad, HttpStatus.OK);
	}
	
//	GET -> consultar TODOS los clientes GET http://localhost:8081/ayabiliaria/listarClientes
	@GetMapping("/listarClientes")
	public ResponseEntity<?> listarClientes() {
		
		System.out.println("Llamando a listarClientes");
		
		ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
		Iterable<Cliente> lista_clientes = null; // con iterable nos da la lista que llama que es servicio

		logger.debug("Atendido por el puerto " + environment.getProperty("local.server.port"));
		lista_clientes = this.ayabiliariaService.consultarTodosClientes(); // dame la lista de clientes y me da el servicio
		responseEntity = ResponseEntity.ok(lista_clientes); // con esto estamos construyendo el objeto de vuelta que
																// es responseEntity
		logger.info("Si, he acabado de listar todos los clientes.");
		
		return responseEntity;
	}
	
//	GET -> consultar TODOS GET http://localhost:8081/restaurante/pagina?page=0&size=2
	@GetMapping("/pagina")
	public ResponseEntity<?> listadoDeClientesPorPagina(Pageable pageable) {

		ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
		Iterable<Cliente> pagina_clientes = null; // con iterable nos da la lista que llama que es servicio

		logger.debug("Atendido por el puerto " + environment.getProperty("local.server.port"));
		pagina_clientes = this.ayabiliariaService.consultarPorPaginas(pageable); // dame la lista de clientes y
																						// me da el servicio
		responseEntity = ResponseEntity.ok(pagina_clientes); // con esto estamos construyendo el objeto de vuelta
																	// que es responseEntity
		// logger.info("Si acabo de listar todos los registros.");
		return responseEntity;
	}
	
//	GET -> consultar TODOS las propiedades GET http://localhost:8081/ayabiliaria/listarPropiedades
	@GetMapping("/listarPropiedades")
	public ResponseEntity<?> listarPropiedades() {
		
		System.out.println("Llamando a listarPropiedades");
		
		ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
		Iterable<Propiedad> lista_propiedades = null; // con iterable nos da la lista que llama que es servicio

		logger.debug("Atendido por el puerto " + environment.getProperty("local.server.port"));
		lista_propiedades = this.ayabiliariaService.consultarTodasPropiedades(); // dame la lista depropiedades y clientes, me da el servicio
		responseEntity = ResponseEntity.ok(lista_propiedades); // con esto estamos construyendo el objeto de vuelta que
																// es responseEntity
		logger.info("Si, he acabado de listar todos las propiedades.");
		
		return responseEntity;
	}
	
	// Dar de alta a un cliente propietario en la inmobiliaria

		@PostMapping("/altaCliente") // POST localhost:8081/ayabiliaria/altaCliente
		public ResponseEntity<?> altaCliente(@Valid Cliente cliente, BindingResult bindingResult) throws IOException { // quitamos el @ResquestBody añadimos multiPartFile archivo

			ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
			Cliente clienteNuevo = null;

			// TODO validar
			if (bindingResult.hasErrors()) {
				logger.debug("Errores en la entrada POST");
				responseEntity = generarRespuestaErroresValidacion(bindingResult);
			} else {
				logger.debug("Sin errores en la entrada POST");
			}

				clienteNuevo = this.ayabiliariaService.altaClienteService(cliente);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo); // 201 es porque se ha creado OK
																									
			return responseEntity;
		}
		
		// TODO  1 Dar de alta una propiedad con foto
		// TODO  2 Debe de controlar que exista el cliente, en caso contrario que permita darlo de alta ¿?
		// Dar de alta una propiedad con foto, y que exista el cliente

		@PostMapping("/altaPropiedad") // POST localhost:8081/ayabiliaria/altaPropiedad
		public ResponseEntity<?> altaPropiedad(@Valid Propiedad propiedad, BindingResult bindingResult,
				MultipartFile archivo) throws IOException { // quitamos el @ResquestBody añadimos multiPartFile archivo

			ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
			Propiedad propiedadNueva = null;

			// TODO validar
			if (bindingResult.hasErrors()) {
				logger.debug("Errores en la entrada POST");
				responseEntity = generarRespuestaErroresValidacion(bindingResult);
			} else {
				logger.debug("Sin errores en la entrada POST");

				if (!archivo.isEmpty()) {
					logger.debug("Restaurante trae foto");
					try {
						propiedad.setFoto(archivo.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.debug("Error al tratar la foto", e);
						throw e; // lanzo la excepción
					}
				}
				
				propiedadNueva.setEstado(0);
				propiedadNueva = this.ayabiliariaService.altaPropiedadService(propiedad);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(propiedadNueva); // 201 es porque se ha
																									// creado
			}
			return responseEntity;
		}
		
		// PUT -> modificar un cliente que ya existe  http://localhost:8081/ayabiliaria/modificaCliente(Body cliente)
		// como ejemplo en postman:  http://localhost:8081/ayabiliaria/modificaCliente/30445332X
		@PutMapping("/modificaCliente/{dni}")
	    public ResponseEntity<Cliente> modificarCliente(@PathVariable String dni, @RequestBody Cliente clienteModificado) {
	        Cliente clienteActualizado = ayabiliariaService.modificarClienteService(dni, clienteModificado);

	        if (clienteActualizado != null) {
	        	System.out.println("Cliente modificado correctamente " + clienteActualizado);
	            return ResponseEntity.ok(clienteActualizado);
	        } else {
	        	System.out.println("Me da error" +  dni + clienteActualizado);
	            return ResponseEntity.notFound().build();
	        }
	    }
		
		// PUT -> modificar una propiedad que ya exista  http://localhost:8081/ayabiliaria/modificarPropiedad(Body cliente)
		// como ejemplo en postman:  http://localhost:8081/ayabiliaria/modificarPropiedad/1
		@PutMapping("/modificarPropiedad/{numeroReferencia}")
		public ResponseEntity<?> modificaPropiedad(@Valid Propiedad propiedad,
				BindingResult bindingResult, MultipartFile archivo, @PathVariable Long numeroReferencia) throws IOException  { // deserializa por recibir un texto

			ResponseEntity<?> responseEntity = null; // representa el mensaje http y devuelve cualquier cosa
			Optional<Propiedad> opPropiedad = null;

			// TODO validar
			if (bindingResult.hasErrors()) {
				logger.debug("Errores en la entrada PUT");
				responseEntity = generarRespuestaErroresValidacion(bindingResult);
			} else {
				logger.debug("Sin errores en la entrada PUT");
				
				if(!archivo.isEmpty()) {
					try {
						propiedad.setFoto(archivo.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("Error al tratar la foto", e);
						throw e;
					}
				}
				
				opPropiedad = this.ayabiliariaService.modificaPropiedadService(numeroReferencia, propiedad);
				if (opPropiedad.isPresent()) {
					Propiedad pr = opPropiedad.get();
					responseEntity = ResponseEntity.ok(pr); // 200 esta correcto
				} else {
					responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 not found error del cliente
				}
			}
			return responseEntity;

		}


		
		// Controlar los errorres y los imprime en el log
		private ResponseEntity<?> generarRespuestaErroresValidacion(BindingResult bindingResult) {

			ResponseEntity<?> responseEntity = null;
			List<ObjectError> listaErrores = null;

			listaErrores = bindingResult.getAllErrors();
			// vamos a imprimir los errores por el log
			listaErrores.forEach(e -> logger.error(e.toString()));
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);

			return responseEntity;
		}
		
}