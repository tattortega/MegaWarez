package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.User;
import com.sofka.megawarez.repository.CategoryRepository;
import com.sofka.megawarez.repository.ItemRepository;
import com.sofka.megawarez.repository.SubcategoryRepository;
import com.sofka.megawarez.service.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class ProductService implements IProduct {

    /**
     * Repositorio de Item
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * Repositorio de Categoria
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Repositorio de Subcategoria
     */
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    /**
     * Devuelve una lista de Items con todos items del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Item> getList() {
        List<Item> items = null;
        try {
            items = (List<Item>) itemRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return items;
    }

    /**
     * Devuelve un item del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Item> findItem(Item item) {
        Optional<Item> items = Optional.empty();
        try {
            items = itemRepository.findById(item.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return items;
    }

    /**
     * Crea un item en el sistema
     *
     * @param item Objeto del item a crear
     * @return Objeto del item creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item createItem(Item item) {
        Item items = null;
        try {
            items = itemRepository.save(item);
            item.setCreatedAt(Instant.now());
        } catch (Exception exc) {
            throw exc;
        }
        return items;
    }

    /**
     * Devuelve una lista de Items con todos items del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de items
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Item> getList(String field, Sort.Direction order) {
        return itemRepository.findAll(Sort.by(order, field));
    }

    /**
     * Actualiza el nombre de un item
     *
     * @param id Identificador del item a actualizar
     * @param item Objeto del item a actualizar
     * @return Objeto del item actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item updateItem(Integer id, Item item) {
        try {
            item.setId(id);
            item.setUpdatedAt(Instant.now());
            itemRepository.updateItem(id, item.getItem());
        } catch (Exception exc) {
            throw exc;
        }
        return item;
    }

    /**
     * Borra un items del sistema
     *
     * @param id Identificación del item a borrar
     * @return Objeto del item borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Item deleteItem(Integer id) {
        var item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return item.get();
        } else {
            return null;
        }
    }
}
