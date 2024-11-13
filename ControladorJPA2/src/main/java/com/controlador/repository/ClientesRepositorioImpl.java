package com.controlador.repository;

import com.controlador.entity.Clientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación personalizada del repositorio {@link ClientesRepositorioCustom} para la entidad {@link Clientes}.
 *
 * Esta clase contiene implementaciones de consultas avanzadas utilizando ->
 * - {@link CriteriaBuilder}
 * - {@link EntityManager}
 *
 * Los métodos implementados son:
 * - {@link #findClientesByAdvancedCriteria(String, String, String, String)}: Permite realizar una búsqueda avanzada
 * - {@link #findClientesByFechaRegistroAfter(java.time.LocalDate)}: Permite filtrar los clientes por fecha de registro
 *
 * @see ClientesRepositorioCustom
 * @see Clientes
 */
@Repository
public class ClientesRepositorioImpl implements ClientesRepositorioCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Realiza una búsqueda avanzada de clientes utilizando varios criterios de filtro y ordenación.
     *
     * @param nombre el nombre del cliente a buscar (opcional, puede ser nulo o vacío).
     * @param apellido el apellido del cliente a buscar (opcional, puede ser nulo o vacío).
     * @param direccion la dirección del cliente a buscar (opcional, puede ser nulo o vacío).
     * @param orderBy el campo por el cual ordenar los resultados (opcional, puede ser nulo o vacío).
     * @return List de clientes que coinciden con los criterios de búsqueda.
     */
    @Override
    public List<Clientes> findClientesByAdvancedCriteria(String nombre, String apellido, String direccion, String orderBy) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Clientes> cq = cb.createQuery(Clientes.class);
        Root<Clientes> clientes = cq.from(Clientes.class);

        // PREDICADO VACIO PARA EMPEZAR LA QUERY
        Predicate predicate = cb.conjunction();

        // ANADIR FILTROS CON LIKE
        if (nombre != null && !nombre.isEmpty()) {
            predicate = cb.and(predicate, cb.like(clientes.get("nombre"), "%" + nombre + "%"));
        }
        if (apellido != null && !apellido.isEmpty()) {
            predicate = cb.and(predicate, cb.like(clientes.get("apellido"), "%" + apellido + "%"));
        }
        if (direccion != null && !direccion.isEmpty()) {
            predicate = cb.and(predicate, cb.like(clientes.get("direccion"), "%" + direccion + "%"));
        }

        // ORDENAR DINAMICAMENTE POR EL CAMPO ELEGIDO
        if (orderBy != null && !orderBy.isEmpty()) {
            cq.orderBy(cb.asc(clientes.get(orderBy)));
        }

        // APLICAMOS LOS FILTROS A LA QUERY
        cq.where(predicate);

        // EJECUTAMOS QUERY Y RETORNAMOS LOS RESULTADOS
        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * Filtra los clientes que se registraron después de la fecha especificada.
     *
     * @param fecha la fecha de registro a partir de la cual se filtran los clientes.
     * @return una lista de clientes que tienen una fecha de registro posterior a la proporcionada.
     */
    @Override
    public List<Clientes> findClientesByFechaRegistroAfter(java.time.LocalDate fecha) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Clientes> cq = cb.createQuery(Clientes.class);
        Root<Clientes> clientes = cq.from(Clientes.class);

        // FILTRO FECHA DE REGISTRO
        Predicate fechaPredicate = cb.greaterThan(clientes.get("fechaRegistro"), fecha);

        // APLICAMOS FILTRO
        cq.where(fechaPredicate);

        // EJECUTAMOS QUERY Y RETORNAMOS LOS RESULTADOS
        return entityManager.createQuery(cq).getResultList();
    }
}
