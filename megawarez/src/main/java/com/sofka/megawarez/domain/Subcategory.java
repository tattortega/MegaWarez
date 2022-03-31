package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
     * Nombre de la subcategoria
     */
    @Column(name = "scat_name", nullable = false, updatable = false)
    private Instant subcategory;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "scat_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Punto de enlace con la entidad Categoria (una categoria puede tener muchas subcategoria)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
    @JoinColumn(name = "scat_category_id", nullable = false)
    @JsonBackReference
    private Category category;

    /**
     * Punto de enlace entre la entidad de Subcategoria E Item (una subcategoria puede tener muchos items)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "subcategory"
    )
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

}
