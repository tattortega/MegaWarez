package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


/**
 * Entidad del producto
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Subcategoria (una subcategoria puede tener muchos productos)
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Subcategory.class,
            optional = false)
    @JoinColumn(name = "prd_subcategory_id", nullable = false)
    @JsonBackReference
    private Subcategory prdSubcategory;

    /**
     * Nombre del producto
     */
    @Column(name = "prd_name", nullable = false, length = 80)
    private String product;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "prd_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada
     */
    @Column(name = "prd_updated_at")
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad del Producto y Descarga (un producto puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Download.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "dwnProduct")
    @JsonManagedReference
    @JsonIgnore
    private List<Download> downloads = new ArrayList<>();

}