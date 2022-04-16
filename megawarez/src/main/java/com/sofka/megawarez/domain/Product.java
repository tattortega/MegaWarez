package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Entidad del producto
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}