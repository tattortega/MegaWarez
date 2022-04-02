package com.sofka.megawarez.service.interfaces;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
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
    public List<Item> getListItem();

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
    public List<Item> getItemOrdered(String field, Sort.Direction order);

    /**
     * Busca un dato dado entre el nombrede un prodcuto
     *
     * @param dataToSearch Dato a buscar
     * @return Lita de productos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Item> searchItem(String dataToSearch);

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


    /**
     * Devuelve una lista de Categorias con todos las categorias del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Category> getListCategory();

    /**
     * Devuelve una categoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Optional<Category> findCategory(Category category);

    /**
     * Crea una categoria en el sistema
     *
     * @param category Objeto de la categoria a crear
     * @return Objeto de la categoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Category createCategory(Category category);

    /**
     * Devuelve una lista de categorias con todos categorias del sistema ordenados por el campo indicado
     * (nombre o fecha) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de categorias
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Category> getCategoryOrdered(String field, Sort.Direction order);

    /**
     * Actualiza el nombre de una categoria basado en su identificador
     *
     * @param id Identificador de la categoria a actualizar
     * @param category Objeto de la categoria a actualizar
     * @return Objeto de la categoria actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Category updateCategory(Integer id, Category category);

    /**
     * Borra una categoria del sistema basado en su identificador
     *
     * @param id Identificación de la categoria a borrar
     * @return Objeto de la categoria borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Category deleteCategory(Integer id);


    /**
     * Devuelve una lista de Subcategorias con todos las subcategorias del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Subcategory> getListSubcategory();

    /**
     * Devuelve una subcategoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Optional<Subcategory> findSubcategory(Subcategory subcategory);

    /**
     * Crea una subcategoria en el sistema
     *
     * @param subcategory Objeto de la subcategoria a crear
     * @return Objeto de la subcategoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Subcategory createSubcategory(Subcategory subcategory);

    /**
     * Devuelve una lista de subcategorias con todos subcategorias del sistema ordenados por el campo indicado
     * (nombre o fecha) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de subcategorias
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<Subcategory> getSubcategoryOrdered(String field, Sort.Direction order);

    /**
     * Actualiza el nombre de una subcategoria basado en su identificador
     *
     * @param id Identificador de la subcategoria a actualizar
     * @param subcategory Objeto de la subcategoria a actualizar
     * @return Objeto de la subcategoria actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Subcategory updateSubcategory(Integer id, Subcategory subcategory);

    /**
     * Borra una subcategoria del sistema basado en su identificador
     *
     * @param id Identificación de la subcategoria a borrar
     * @return Objeto de la subcategoria borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Subcategory deleteSubcategory(Integer id);
}
