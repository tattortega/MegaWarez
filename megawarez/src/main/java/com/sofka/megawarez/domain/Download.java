package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Entidad de la descarga
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
@Table(name = "download")
public class Download implements Serializable {


    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dwn_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Usuario (un usuario puede tener muchas descargas)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
    @JoinColumn(name = "dwn_user_id")
    @JsonBackReference
    @ToString.Exclude
    private User dwnUser;

    /**
     * Punto de enlace con la entidad Producto (un producto puede tener muchas descargas)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class, optional = false)
    @JoinColumn(name = "dwn_product_id", nullable = false)
    @JsonBackReference(value = "second")
    @ToString.Exclude
    private Product dwnProduct;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "dwn_created_at", nullable = false)
    private Instant createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Download download = (Download) o;
        return id != null && Objects.equals(id, download.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}