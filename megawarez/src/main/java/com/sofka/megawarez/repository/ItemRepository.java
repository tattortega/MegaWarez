package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Busca los prodcutos que empiezan por X dato tanto por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT itm " +
            "FROM Item itm " +
            "WHERE (itm.item LIKE :data%) " +
            "ORDER BY itm.item ASC")
    public List<Item> findByNombreStartingWith(@Param("data") String data);

    /**
     * Busca los productos que contienen X dato  por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT itm " +
            "FROM Item itm " +
            "WHERE itm.item LIKE %:data% " +
            "ORDER BY itm.item ASC")
    public List<Item> findByNombreContains(@Param("data") String data);

    /**
     * Busca los productos que finalizan por X dato por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT itm " +
            "FROM Item itm " +
            "WHERE itm.item LIKE %:data " +
            "ORDER BY itm.item ASC")
    public List<Item> findByNombreEndingWith(@Param("data") String data);

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
