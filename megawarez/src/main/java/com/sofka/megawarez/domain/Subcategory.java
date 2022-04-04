package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de la subcategoria
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scat_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Categoria (una categoria puede tener muchas subcategoria)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class, optional = false)
    @JoinColumn(name = "scat_category_id")
    @JsonBackReference
    private Category scatCategory;

    /**
     * Nombre de la subcategoria
     */
    @Column(name = "scat_name", nullable = false, length = 80)
    private String subcategory;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "scat_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Punto de enlace entre la entidad de Subcategoria y Producto (una subcategoria puede tener muchos productos)
     */
    @OneToMany(fetch = FetchType.EAGER,
            targetEntity = Product.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "prdSubcategory")
    @JsonManagedReference
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

}