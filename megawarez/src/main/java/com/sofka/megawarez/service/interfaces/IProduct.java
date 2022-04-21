package com.sofka.megawarez.service.interfaces;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Product;
import com.sofka.megawarez.domain.Subcategory;
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
     * Devuelve una lista de productos del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    List<Product> getListProducts();

    /**
     * Devuelve un producto del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Optional<Product> findProduct(Product product);

    /**
     * Crea un producto en el sistema
     *
     * @param product Objeto del producto a crear
     * @return Objeto del producto creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Product createProduct(Product product);

    /**
     * Devuelve una lista de productos del sistema ordenados por el campo indicado
     * (nombre o fecha) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de productos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    List<Product> getProductOrdered(String field, Sort.Direction order);

    /**
     * Busca un dato dado entre el nombre de un producto
     *
     * @param dataToSearch Dato a buscar
     * @return Lita de productos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    List<Product> searchProduct(String dataToSearch);

    /**
     * Actualiza el nombre de un producto basado en su identificador
     *
     * @param id Identificador del producto a actualizar
     * @param product Objeto del producto a actualizar
     * @return Objeto del producto actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Product updateProduct(Integer id, Product product);

    /**
     * Borra un producto del sistema basado en su identificador
     *
     * @param id Identificación del producto a borrar
     * @return Objeto del producto borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Product deleteProduct(Integer id);


    /**
     * Devuelve una lista de Categorias con todos las categorias del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    List<Category> getListCategory();

    /**
     * Devuelve una categoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Optional<Category> findCategory(Category category);

    /**
     * Crea una categoria en el sistema
     *
     * @param category Objeto de la categoria a crear
     * @return Objeto de la categoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Category createCategory(Category category);

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
    List<Category> getCategoryOrdered(String field, Sort.Direction order);

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
    List<Subcategory> getListSubcategory();

    /**
     * Devuelve una subcategoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Optional<Subcategory> findSubcategory(Subcategory subcategory);

    /**
     * Crea una subcategoria en el sistema
     *
     * @param subcategory Objeto de la subcategoria a crear
     * @return Objeto de la subcategoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    Subcategory createSubcategory(Subcategory subcategory);

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
    List<Subcategory> getSubcategoryOrdered(String field, Sort.Direction order);

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
