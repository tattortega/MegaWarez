package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Actualiza el nombre de una categoria basado en su identificador
     *
     * @param id Identificador de la categoria
     * @param category Nuevo nombre de la categoria
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update Category cat set cat.category = :category, cat.updatedAt = CURRENT_TIMESTAMP where cat.id = :id")
    public void updateCategory(@Param(value = "id") Integer id, @Param(value = "category") String category);

}