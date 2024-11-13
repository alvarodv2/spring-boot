package com.controlador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Esta clase contiene el punto de entrada de la aplicación y se encarga de inicializar y ejecutar Spring Boot.
 * Se arranca servidor embebido y la aplicación comienza a funcionar.
 */
@SpringBootApplication
public class ControladorJPA {

	/**
	 * Metodo principal de la aplicación.
	 * Este metodo arranca la aplicación Spring Boot.
	 *
	 * @param args los argumentos de línea de comandos pasados al ejecutar la aplicación.
	 * @see SpringApplication#run(Class, String...)
	 */
	public static void main(String[] args) {
		SpringApplication.run(ControladorJPA.class, args);
	}
}
