package com.controlador.repository;

import com.controlador.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad {@link Clientes}.
 * Extiende de {@link JpaRepository} para operaciones CRUD básicas
 * Extiende también de {@link ClientesRepositorioCustom} para consultas personalizadas
 * adicionales relacionadas con los clientes.
 *
 * Este repositorio se utiliza para interactuar con la base de datos y realizar operaciones sobre{@link Clientes}.
 *
 * La interfaz también extiende {@link ClientesRepositorioCustom}, lo que permite incluir consultas personalizadas
 * adicionales como búsquedas avanzadas de clientes.
 */
public interface ClientesRepositorio extends JpaRepository<Clientes, Long>, ClientesRepositorioCustom {

}
