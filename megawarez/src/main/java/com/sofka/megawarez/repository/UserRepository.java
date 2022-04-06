package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Actualiza el nombre de un usuario basado en su identificador
     *
     * @param id Identificador del usuario
     * @param username Nuevo nombre del usuario
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update User use set use.username = :username, use.updatedAt = CURRENT_TIMESTAMP where use.id = :id")
    public void updateUsername(@Param(value = "id") Integer id, @Param(value = "username") String username);

    /**
     * Actualiza la contraseña de un usuario basado en su identificador
     *
     * @param id Identificador del usuario
     * @param password Nuevo contraseña del usuario
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update User use set use.password = :password, use.updatedAt = CURRENT_TIMESTAMP where use.id = :id")
    public void updatePassword(@Param(value = "id") Integer id, @Param(value = "password") String password);

    /**
     * Busca un usuario por el nombre
     * @param username Nombre del usuario
     * @return Optional
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Optional<User> findByUsername(String username);

}