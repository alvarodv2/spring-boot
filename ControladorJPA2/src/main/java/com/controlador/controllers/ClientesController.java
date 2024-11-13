package com.controlador.controllers;

import com.controlador.entity.Clientes;
import com.controlador.repository.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD y consultas avanzadas sobre los clientes.
 * Proporciona ENDPOINTS para obtener, crear y filtrar clientes en la DATA BASE.
 */
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    /**
     * Obtiene todos los clientes almacenados en la base de datos.
     *
     * @return List de todos los clientes.
     */
    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesRepositorio.findAll();
    }

    /**
     * Realiza una consulta avanzada de clientes con filtros {nombre, apellido, dirección y ordenación}.
     *
     * @param nombre El nombre del cliente (opcional).
     * @param apellido El apellido del cliente (opcional).
     * @param direccion La dirección del cliente (opcional).
     * @param orderBy El campo por el que ordenar los resultados (opcional).
     * @return Una lista de clientes que coinciden con los criterios de búsqueda.
     * @throws ResponseStatusException Si ocurre un error durante la consulta.
     */
    @GetMapping("/buscar")
    public List<Clientes> findClientesByCriteria(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String orderBy) {
        try {
            return clientesRepositorio.findClientesByAdvancedCriteria(nombre, apellido, direccion, orderBy);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en la consulta", e);
        }
    }

    /**
     * Filtra los clientes que se registraron después de una fecha específica.
     *
     * @param fecha Fecha registro.
     * @return List clientes registrados después de la fecha proporcionada.
     */
    @GetMapping("/fecha-registro")
    public List<Clientes> findClientesByFechaRegistroAfter(@RequestParam String fecha) {
        LocalDate fechaRegistro = LocalDate.parse(fecha);
        return clientesRepositorio.findClientesByFechaRegistroAfter(fechaRegistro);
    }

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a crear.
     * @return El cliente creado, con su identificador asignado.
     */
    @PostMapping
    public Clientes createCliente(@RequestBody Clientes cliente) {
        return clientesRepositorio.save(cliente);
    }
}
