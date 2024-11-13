package com.controlador.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Representa una entidad de "cliente" en la base de datos.
 * Cada cliente tiene un identificador único -> {nombre, apellido, dirección, fecha de registro}.
 * Esta clase se mapea a la tabla "clientes" en la base de datos.
 */
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nombre", unique = true, length = 40)
    private String nombre;

    @Column(name = "apellido", length = 40)
    private String apellido;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    /**
     * Constructor vacío requerido por JPA.
     * Inicializa un nuevo objeto de tipo Clientes sin establecer valores.
     */
    public Clientes() {}

    /**
     * Constructor con parámetros para inicializar un cliente.
     *
     * @param nombre El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param direccion La dirección del cliente.
     * @param fechaRegistro La fecha en la que el cliente fue registrado.
     */
    public Clientes(String nombre, String apellido, String direccion, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha de registro del cliente.
     *
     * @return La fecha de registro del cliente.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del cliente.
     *
     * @param fechaRegistro La fecha de registro del cliente a establecer.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
