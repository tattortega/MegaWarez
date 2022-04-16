package com.sofka.megawarez.service;

import com.sofka.megawarez.domain.Download;
import com.sofka.megawarez.domain.Session;
import com.sofka.megawarez.domain.User;
import com.sofka.megawarez.repository.DownloadRepository;
import com.sofka.megawarez.repository.SessionRepository;
import com.sofka.megawarez.repository.UserRepository;
import com.sofka.megawarez.service.interfaces.IUser;
import com.sofka.megawarez.utility.LoginData;
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

    @Autowired
    private LoginData loginData;

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
    public List<User> getListUser() {
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
     * Busca un usuario por el nombre
     * @param username
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    public User findByUsername(String username){
        User users = null;
        try {
            users = userRepository.findByUsername(username).orElse(new User());
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
    public User createUser(User user) throws Exception {
        User users = null;
        try {
            user.setCreatedAt(Instant.now());
            user.setPassword(loginData.setPassword(user.getPassword()));
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
    public User updatePassword(Integer id, User user) throws Exception {
        try {
            user.setId(id);
            user.setUpdatedAt(Instant.now());
            user.setPassword(loginData.setPassword(user.getPassword()));
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

    /**
     * Devuelve una lista de Session del sistema
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    public List<Session> getListSession() {
        return null;
    }

    /**
     * Devuelve una session
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    public Optional<Session> findSession(Session session) {
        return Optional.empty();
    }

    /**
     * Crea una session para un  usuario en el sistema
     *
     * @param session Objeto de la session a crear
     * @return Objeto de la session creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    public Session createSession(LoginData loginData, User user, Session session) throws Exception {
        Session sessions = null;
        try {
            session.setCreatedAt(Instant.now());
            session.setToken(loginData.getToken());
            User id = userRepository.getById(user.getId());
            session.setSesUser(id);
            sessions = sessionRepository.save(session);
        } catch (Exception exc) {
            throw exc;
        }
        return sessions;
    }

    /**
     * Borra una session del sistema
     *
     * @param id Identificación de la session a borrar
     * @return Objeto de la session borrado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    public Session deleteSession(Integer id) {
        return null;
    }

    /**
     * Devuelve una lista de Descargas del usuario
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Download> getListDownload() {
        List<Download> downloads = null;
        try {
            downloads = (List<Download>) downloadRepository.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return downloads;
    }

    /**
     * Devuelve una descarga del usuario
     *
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Download> findDownload(Download download) {
        Optional<Download> downloads = Optional.empty();
        try {
            downloads = downloadRepository.findById(download.getId());
        } catch (Exception exc) {
            throw exc;
        }
        return downloads;
    }

    /**
     * Crea una descarga para el usuario en el sistema
     *
     * @param download Objeto de la descarga a crear
     * @return Objeto de la descarga creado
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Download createDownload(Download download) {
        Download downloads = null;
        try {
            download.setCreatedAt(Instant.now());
            downloads = downloadRepository.save(download);
        } catch (Exception exc) {
            throw exc;
        }
        return downloads;
    }
}
