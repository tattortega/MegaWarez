package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.Subcategory;
import com.sofka.megawarez.repository.CategoryRepository;
import com.sofka.megawarez.repository.ItemRepository;
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
    public List<Item> getListItem() {
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
            item.setCreatedAt(Instant.now());
            items = itemRepository.save(item);
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
    public List<Item> getItemOrdered(String field, Sort.Direction order) {
        return itemRepository.findAll(Sort.by(order, field));
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
    public List<Item> searchItem(String dataToSearch) {
        var item1 = itemRepository.findByNombreStartingWith(dataToSearch);
        var item2 = itemRepository.findByNombreContains(dataToSearch);
        var item3 = itemRepository.findByNombreEndingWith(dataToSearch);
        var answer = new HashSet<Item>();
        answer.addAll(item1);
        answer.addAll(item2);
        answer.addAll(item3);
        return answer.stream().toList();
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
     * Borra un item del sistema
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
            categories = categoryRepository.save(category);
            categories.setCreatedAt(Instant.now());
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
     * Actualiza el nombre de una categoria
     *
     * @param id Identificador de la categoria a actualizar
     * @param category Objeto de la categoria a actualizar
     * @return Objeto de la categoria actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Category updateCategory(Integer id, Category category) {
        try {
            category.setId(id);
            category.setUpdatedAt(Instant.now());
            categoryRepository.updateCategory(id, category.getCategory());
        } catch (Exception exc) {
            throw exc;
        }
        return category;
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
            subcategories = subcategoryRepository.save(subcategory);
            subcategories.setCreatedAt(Instant.now());
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
     * Actualiza el nombre de una subcategoria
     *
     * @param id Identificador de la subcategoria a actualizar
     * @param subcategory Objeto de la subcategoria a actualizar
     * @return Objeto de la subcategoria actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategory updateSubcategory(Integer id, Subcategory subcategory) {
        try {
            subcategory.setId(id);
            subcategory.setUpdatedAt(Instant.now());
            subcategoryRepository.updateSubcategory(id, subcategory.getSubcategory());
        } catch (Exception exc) {
            throw exc;
        }
        return subcategory;
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
