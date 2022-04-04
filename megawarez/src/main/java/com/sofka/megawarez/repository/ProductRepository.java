package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Busca los productos que empiezan por X dato tanto por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT prd " +
            "FROM Product prd " +
            "WHERE (prd.product LIKE :data%) " +
            "ORDER BY prd.product ASC")
    public List<Product> findByNombreStartingWith(@Param("data") String data);

    /**
     * Busca los productos que contienen X dato  por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT prd " +
            "FROM Product prd " +
            "WHERE prd.product LIKE %:data% " +
            "ORDER BY prd.product ASC")
    public List<Product> findByNombreContains(@Param("data") String data);

    /**
     * Busca los productos que finalizan por X dato por nombre
     *
     * @param data Dato a buscar
     * @return Listado de productos encontrados
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT prd " +
            "FROM Product prd " +
            "WHERE prd.product LIKE %:data " +
            "ORDER BY prd.product ASC")
    public List<Product> findByNombreEndingWith(@Param("data") String data);

    /**
     * Actualiza el nombre de un item basado en su identificador
     *
     * @param id Identificador del producto
     * @param product Nuevo nombre del producto
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update Product prd set prd.product = :product, prd.updatedAt = CURRENT_TIMESTAMP where prd.id = :id")
    public void updateProduct(@Param(value = "id") Integer id, @Param(value = "product") String product);

}
