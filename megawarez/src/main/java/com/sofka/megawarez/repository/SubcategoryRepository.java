package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    /**
     * Selecciona las subcategorias de una categoria en específico
     *
     * @param category Objeto del contacto
     * @return Listado de subcategorias encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT scat FROM Subcategory scat WHERE scat.scatCategory = :category")
    public List<Subcategory> findAllBySubcategory(@Param(value = "category") Category category);
}
