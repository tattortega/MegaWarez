package com.sofka.megawarez.controller;

import com.sofka.megawarez.domain.Download;
import com.sofka.megawarez.domain.User;
import com.sofka.megawarez.service.UserService;
import com.sofka.megawarez.utility.LoginData;
import com.sofka.megawarez.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Controlador para el Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
public class UserController {

    /**
     * Servicio para el manejo del Usuario
     */
    @Autowired
    private UserService userService;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;


    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema y versión, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index del sistema, responde con el listado de usuarios
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/users")
    public ResponseEntity<Response> users() {
        response.restart();
        try {
            response.data = userService.getListUser();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Administrador para la redirección al controllador /api/v1/users
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/users");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El usuario ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * Crea un nuevo usuario en el sistema
     *
     * @param user Objeto Usuario a crear
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/user")
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        response.restart();
        try {
            log.info("Usuario a crear: {}", user);
            User u = this.userService.findByUsername(user.getUsername());
            if (u.getId() == null) {
                response.data = userService.createUser(user);
                response.message = "El usuario se ha registrado correctamente";
                httpStatus = HttpStatus.CREATED;
            } else {
                response.message = "El usuario ya se encuentra registrado";
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea el token para el Usuario
     *
     * @param loginData
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/login")
    public ResponseEntity<Response> login(@RequestBody LoginData loginData) {
        response.restart();
        try {
            User user = this.userService.findByUsername(loginData.getUsername());
            if (user.getUsername() != null) {
                response.message = "Session iniciada";
                response.data = loginData.getToken();
                httpStatus = HttpStatus.OK;
            } else {
                response.message = "El usuario no se encuentra registrado";
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Obtiene el token del Usuario logueado
     *
     * @param authorization
     * @return
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/token")
    public ResponseEntity<Response> getToken(@RequestHeader("Authorization") String authorization) {
        response.restart();
        try {
            response.message = "Todo OK - TOKEN";
            response.data = authorization.replace("Bearer ", "");
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }
    /**
     * Actualiza el nombre de un usuario basado en su identificador
     *
     * @param user Objeto Usuario
     * @param id Identificador del usuario a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/user/{id}/username")
    public ResponseEntity<Response> updateUsername(
            @RequestBody User user,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = userService.updateUsername(id, user);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza la contraseña de un usuario basado en su identificador
     *
     * @param user Objeto Usuario
     * @param id Identificador del usuario a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/user/{id}/password")
    public ResponseEntity<Response> updatePassword(
            @RequestBody User user,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = userService.updatePassword(id, user);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Borra un usuario del sistema
     *
     * @param id Identificador del usuario a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/user/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = userService.deleteUser(id);
            if (response.data == null) {
                response.message = "El usuario no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El usuario fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }



    /**
     * Index de descargas, responde con el listado de descargas
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/downloads")
    public ResponseEntity<Response> download() {
        response.restart();
        try {
            response.data = userService.getListDownload();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Obtiene una descarga segun el identificador
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     * @param id
     */
    @GetMapping(path = "/api/v1/download/{id}")
    public ResponseEntity<Response> findDownload(@PathVariable(value="id") Download id) {
        response.restart();
        try {
            response.data = userService.findDownload(id);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva descarga en el sistema
     *
     * @param download Objeto descarga a crear
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/download")
    public ResponseEntity<Response> createDownload(@RequestBody Download download) {
        response.restart();
        try {
            log.info("Descarga a crear: {}", download);
            response.data = userService.createDownload(download);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


}
