package com.controlador.repository;

import com.controlador.entity.Clientes;

import java.util.List;

public interface ClientesRepositorioCustom {

    List<Clientes> findClientesByAdvancedCriteria(String nombre, String apellido, String direccion, String ordenPor);

    List<Clientes> findClientesByFechaRegistroAfter(java.time.LocalDate fecha);
}
