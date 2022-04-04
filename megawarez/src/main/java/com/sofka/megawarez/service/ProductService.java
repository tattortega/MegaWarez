package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Product;
import com.sofka.megawarez.domain.Subcategory;
import com.sofka.megawarez.repository.CategoryRepository;
import com.sofka.megawarez.repository.ProductRepository;
import com.sofka.megawarez.repository.SubcategoryRepository;
import com.sofka.megawarez.service.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProduct {

    /**
     * Repositorio de Item
     */
    @Autowired
    private ProductRepository productRepository;

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
     * Devuelve una lista de productos del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getListProducts() {
        List<Product> products = null;
        try {
            products = (List<Product>) productRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return products;
    }

    /**
     * Devuelve un producto del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findProduct(Product product) {
        Optional<Product> products = Optional.empty();
        try {
            products = productRepository.findById(product.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return products;
    }

    /**
     * Crea un producto en el sistema
     *
     * @param product Objeto del producto a crear
     * @return Objeto del producto creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Product createProduct(Product product) {
        Product products = null;
        try {
            product.setCreatedAt(Instant.now());
            products = productRepository.save(product);
        } catch (Exception exc) {
            throw exc;
        }
        return products;
    }

    /**
     * Devuelve una lista de productos del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de productos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductOrdered(String field, Sort.Direction order) {
        return productRepository.findAll(Sort.by(order, field));
    }

    /**
     * Busca un dato entre el nombre de un producto
     *
     * @param dataToSearch Dato a buscar
     * @return Lita de productos
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProduct(String dataToSearch) {
        var product1 = productRepository.findByNombreStartingWith(dataToSearch);
        var product2 = productRepository.findByNombreContains(dataToSearch);
        var product3 = productRepository.findByNombreEndingWith(dataToSearch);
        var answer = new HashSet<Product>();
        answer.addAll(product1);
        answer.addAll(product2);
        answer.addAll(product3);
        return answer.stream().toList();
    }

    /**
     * Actualiza el nombre de un producto
     *
     * @param id Identificador del producto a actualizar
     * @param product Objeto del producto a actualizar
     * @return Objeto del producto actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Product updateProduct(Integer id, Product product) {
        try {
            product.setId(id);
            product.setUpdatedAt(Instant.now());
            productRepository.updateProduct(id, product.getProduct());
        } catch (Exception exc) {
            throw exc;
        }
        return product;
    }

    /**
     * Borra un producto del sistema
     *
     * @param id Identificación del producto a borrar
     * @return Objeto del producto borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Product deleteProduct(Integer id) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return product.get();
        } else {
            return null;
        }
    }

    /**
     * Devuelve una lista de Categorias con todos categorias del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getListCategory() {
        List<Category> category = null;
        try {
            category = (List<Category>) categoryRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return category;
    }

    /**
     * Devuelve una categoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findCategory(Category category) {
        Optional<Category> categories = Optional.empty();
        try {
            categories = categoryRepository.findById(category.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return categories;
    }

    /**
     * Crea una categoria en el sistema
     *
     * @param category Objeto de la categoria a crear
     * @return Objeto de la categoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category createCategory(Category category) {
        Category categories = null;
        try {
            category.setCreatedAt(Instant.now());
            categories = categoryRepository.save(category);
        } catch (Exception exc) {
            throw exc;
        }
        return categories;
    }

    /**
     * Devuelve una lista de Categorias con todos categorias del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de categorias
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryOrdered(String field, Sort.Direction order) {
        return categoryRepository.findAll(Sort.by(order, field));
    }

    /**
     * Borra una categoria del sistema
     *
     * @param id Identificación de la categoria a borrar
     * @return Objeto de la categoria borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category deleteCategory(Integer id) {
        var category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return category.get();
        } else {
            return null;
        }
    }


    /**
     * Devuelve una lista de Subcategorias con todos subcategorias del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subcategory> getListSubcategory() {
        List<Subcategory> subcategory = null;
        try {
            subcategory = (List<Subcategory>) subcategoryRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return subcategory;
    }

    /**
     * Devuelve una subcategoria del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Subcategory> findSubcategory(Subcategory subcategory) {
        Optional<Subcategory> subcategories = Optional.empty();
        try {
            subcategories = subcategoryRepository.findById(subcategory.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return subcategories;
    }

    /**
     * Crea una subcategoria en el sistema
     *
     * @param subcategory Objeto de la subcategoria a crear
     * @return Objeto de la subcategoria creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory createSubcategory(Subcategory subcategory) {
        Subcategory subcategories = null;
        try {
            subcategory.setCreatedAt(Instant.now());
            subcategories = subcategoryRepository.save(subcategory);
        } catch (Exception exc) {
            throw exc;
        }
        return subcategories;
    }

    /**
     * Devuelve una lista de Subcategorias con todos subcategorias del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de subcategorias
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subcategory> getSubcategoryOrdered(String field, Sort.Direction order) {
        return subcategoryRepository.findAll(Sort.by(order, field));
    }

    /**
     * Borra una subcategoria del sistema
     *
     * @param id Identificación de la subcategoria a borrar
     * @return Objeto de la subcategoria borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory deleteSubcategory(Integer id) {
        var subcategory = subcategoryRepository.findById(id);
        if (subcategory.isPresent()) {
            subcategoryRepository.delete(subcategory.get());
            return subcategory.get();
        } else {
            return null;
        }
    }

}
