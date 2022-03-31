package com.sofka.megawarez.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad del Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id", nullable = false)
    private Integer id;

    /**
     * Nombre del usuario
     */
    @Column(name = "use_username", nullable = false, length = 100)
    private String username;

    /**
     * Contraseña del usuario
     */
    @Column(name = "use_password", nullable = false, length = 100)
    private String password;

    /**
     * Fecha y hora en que la tupla ha sido creada
     */
    @Column(name = "use_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Fecha y hora en que la tupla ha sido actualizada por última vez
     */
    @Column(name = "use_updated_at")
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad del Usuario y Sesion (un usuario puede tener muchas sesiones abiertas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Session> sessions = new ArrayList<>();

    /**
     * Punto de enlace entre la entidad del Usuario y Descarga (un usuario puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Download> downloads = new ArrayList<>();
}
