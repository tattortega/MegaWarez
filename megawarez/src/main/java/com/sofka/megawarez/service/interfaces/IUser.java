package com.sofka.megawarez.service.interfaces;

import com.sofka.megawarez.domain.Item;
import com.sofka.megawarez.domain.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Interface para el servicio de Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
public interface IUser {

    /**
     * Devuelve una lista de Usuarios con todos los usuarios del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public List<User> getList();

    /**
     * Devuelve un usuario del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public Optional<User> findUser(User user);

    /**
     * Crea un usuario en el sistema
     *
     * @param user Objeto del usuario a crear
     * @return Objeto del usuario creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public User createUser(User user);

    /**
     * Actualiza el nombre de un usuario basado en su identificador
     *
     * @param id Identificador del usuario a actualizar
     * @param user Objeto del usuario a actualizar
     * @return Objeto del usuario actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public User updateUsername(Integer id, User user);

    /**
     * Actualiza la contraseña de un usuario basado en su identificador
     *
     * @param id Identificador del usuario a actualizar
     * @param user Objeto del usuario a actualizar
     * @return Objeto del usuario actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public User updatePassword(Integer id, User user);

    /**
     * Borra un usuario del sistema basado en su identificador
     *
     * @param id Identificación del usuario a borrar
     * @return Objeto del usuario borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    User deleteUser(Integer id);

}
