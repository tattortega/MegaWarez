package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Actualiza el nombre de un item basado en su identificador
     *
     * @param id Identificador del item
     * @param item Nuevo nombre del item
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update Item it set it.item = :item, it.updatedAt = CURRENT_TIMESTAMP where it.id = :id")
    public void updateItem(@Param(value = "id") Integer id, @Param(value = "item") String item);

}
