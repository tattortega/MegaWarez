package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


/**
 * Entidad del item
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id", nullable = false)
    private Integer id;

    /**
     * Nombre del item
     */
    @Column(name = "itm_name", nullable = false, updatable = false)
    private Instant item;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "itm_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Punto de enlace con la entidad Subcategoria (una subcategoria puede tener muchos items)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
    @JoinColumn(name = "itm_subcategory_id", nullable = false)
    @JsonBackReference
    private Subcategory subcategory;

    /**
     * Punto de enlace entre la entidad del Item y Descarga (un item puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "item"
    )
    @JsonManagedReference
    private List<Download> downloads = new ArrayList<>();

}


