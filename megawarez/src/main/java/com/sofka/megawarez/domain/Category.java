package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de la categoria
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Integer id;

    /**
     * Nombre de la categoria
     */
    @Column(name = "cat_name", nullable = false, length = 80)
    private String category;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "cat_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada
     */
    @Column(name = "cat_updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad de Categoria y Subcategoria (una categoria puede tener muchas subcategorias)
     */
    @OneToMany(fetch = FetchType.EAGER,
            targetEntity = Subcategory.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "scatCategory")
    @JsonManagedReference
    private List<Subcategory> subcategories = new ArrayList<>();

}