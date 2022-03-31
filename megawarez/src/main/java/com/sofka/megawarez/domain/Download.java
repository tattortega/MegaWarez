package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

/**
 * Entidad de la descarga
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
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
    private User dwnUser;

    /**
     * Punto de enlace con la entidad Item (un item puede tener muchas descargas)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class, optional = false)
    @JoinColumn(name = "dwn_item_id", nullable = false)
    @JsonBackReference
    private Item dwnItem;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "dwn_created_at", nullable = false)
    private Instant createdAt;

}