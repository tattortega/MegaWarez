package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.User;
import com.sofka.megawarez.repository.DownloadRepository;
import com.sofka.megawarez.repository.SessionRepository;
import com.sofka.megawarez.repository.UserRepository;
import com.sofka.megawarez.service.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Clase tipo Servicio para el manejo de Usuarios
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Service
public class UserService implements IUser {

    /**
     * Repositorio de Usuario
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Repositorio de Descarga
     */
    @Autowired
    private DownloadRepository downloadRepository;

    /**
     * Repositorio de Session
     */
    @Autowired
    private SessionRepository sessionRepository;

    /**
     * Devuelve una lista de Usuarios con todos usuarios del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        List<User> users = null;
        try {
            users = (List<User>) userRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return users;
    }

    /**
     * Devuelve un usuario del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUser(User user) {
        Optional<User> users = Optional.empty();
        try {
            users = userRepository.findById(user.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return users;
    }

    /**
     * Crea un usuario en el sistema
     *
     * @param user Objeto del usuario a crear
     * @return Objeto del usuario creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public User createUser(User user) {
        User users = null;
        try {
            user.setCreatedAt(Instant.now());
            users = userRepository.save(user);
        } catch (Exception exc) {
            throw exc;
        }
        return users;
    }

    /**
     * Actualiza el nombre de un usuario
     *
     * @param id Identificador del usuario a actualizar
     * @param user Objeto del usuario a actualizar
     * @return Objeto del usuario actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public User updateUsername(Integer id, User user) {
        try {
            user.setId(id);
            user.setUpdatedAt(Instant.now());
            userRepository.updateUsername(id, user.getUsername());
        } catch (Exception exc) {
            throw exc;
        }
        return user;
    }

    /**
     * Actualiza la contraseña de un usuario
     *
     * @param id Identificador del usuario a actualizar
     * @param user Objeto del usuario a actualizar
     * @return Objeto del usuario actualizado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public User updatePassword(Integer id, User user) {
        try {
            user.setId(id);
            user.setUpdatedAt(Instant.now());
            userRepository.updatePassword(id, user.getPassword());
        } catch (Exception exc) {
            throw exc;
        }
        return user;
    }

    /**
     * Borra un usuario del sistema
     *
     * @param id Identificación del usuario a borrar
     * @return Objeto del usuario borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public User deleteUser(Integer id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return user.get();
        } else {
            return null;
        }
    }
}
