package com.ayavoy.inmobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
//@EnableEurekaClient    // Activamos el cliente Eureka
@Controller
public class AyabiliariaApplication {
	
	/**
	 * PARA CONFIGURAR EL CLIENTE EUREKA
	 * 
	 * 1 Add Starters, eureka client
	 * 2 Add anotación @EnableEurekaClient
	 * 3 Configurar las properties relativas al eureka
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(AyabiliariaApplication.class, args);
	}

@RequestMapping(value = "{path:[^\\.]*}", method = RequestMethod.GET)
    public String redirect() {
        return "forward:/";
    }


}
