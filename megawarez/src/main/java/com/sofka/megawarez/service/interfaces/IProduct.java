package com.sofka.megawarez.service.interfaces;

import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.User;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

/**
 * Interface para el servicio de Productos
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
public interface IProduct {

    /**
     * Devuelve una lista de items con todos los items del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Item> getList();

    /**
     * Devuelve un item del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Optional<Item> findItem(Item item);

    /**
     * Crea un item en el sistema
     *
     * @param item Objeto del item a crear
     * @return Objeto del item creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Item createItem(Item item);

    /**
     * Devuelve una lista de items con todos items del sistema ordenados por el campo indicado
     * (nombre o fecha) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de contactos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Item> getList(String field, Sort.Direction order);

    /**
     * Actualiza el nombre de un item basado en su identificador
     *
     * @param id Identificador del item a actualizar
     * @param item Objeto del item a actualizar
     * @return Objeto del item actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Item updateItem(Integer id, Item item);

    /**
     * Borra un item del sistema basado en su identificador
     *
     * @param id Identificación del item a borrar
     * @return Objeto del item borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Item deleteItem(Integer id);
}
