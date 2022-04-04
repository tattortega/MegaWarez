package com.sofka.megawarez.controller;

import com.sofka.megawarez.domain.Category;
import com.sofka.megawarez.domain.Product;
import com.sofka.megawarez.domain.Subcategory;
import com.sofka.megawarez.service.ProductService;
import com.sofka.megawarez.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para el Producto
 *
 * @version 1.0.0 2022-03-31
 * @author Ricardo Ortega <tattortega.28@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
public class ProductController {


    /**
     * Servicio para el manejo de Producto
     */
    @Autowired
    private ProductService productService;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;


    /**
     * Index de productos, responde con el listado de productos
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/products")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = productService.getListProducts();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
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
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El dato indicado no existe";
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
     * Crea un nuevo producto en el sistema
     *
     * @param product Objeto producto a crear
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/product")
    public ResponseEntity<Response> createProduct(@RequestBody Product product) {
        response.restart();
        try {
            log.info("Producto a crear: {}", product);
            response.data = productService.createProduct(product);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza el nombre de un producto basado en su identificador
     *
     * @param product Objeto producto
     * @param id Identificador del producto a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/product/{id}/product")
    public ResponseEntity<Response> updateProduct(
            @RequestBody Product product,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = productService.updateProduct(id, product);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza la subcategoria de un producto basado en su identificador
     *
     * @param product Objeto producto
     * @param id Identificador del usuario a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/product/{id}/subcategory")
    public ResponseEntity<Response> updateProductSubcategory(
            @RequestBody Product product,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = productService.updateProduct(id, product);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Borra un producto del sistema
     *
     * @param id Identificador del producto a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/product/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = productService.deleteProduct(id);
            if (response.data == null) {
                response.message = "El producto no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El producto fue removido exitosamente";
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
     * Devuelve todos las subcategorias con sus productos ordenados por nombre o fecha de forma ascendente o descendente
     *
     * @param orderBy Nombre del campo por donde se desea ordenar la información
     * @param order Tipo de orden que debe tener la información ASC o DESC
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/products/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = productService.getProductOrdered(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve el listado de productos basados en un dato a buscar por nombre
     *
     * @param dataToSearch Información a buscar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/search/product/{dataToSearch}")
    public ResponseEntity<Response> searchProduct(
            @PathVariable(value="dataToSearch") String dataToSearch
    ) {
        response.restart();
        try {
            response.data = productService.searchProduct(dataToSearch);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }



    /**
     * Index de categorias, responde con el listado de categorias
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/category")
    public ResponseEntity<Response> category() {
        response.restart();
        try {
            response.data = productService.getListCategory();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva categoria en el sistema
     *
     * @param category Objeto categoria a crear
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/category")
    public ResponseEntity<Response> createCategory(@RequestBody Category category) {
        response.restart();
        try {
            log.info("Categoria a crear: {}", category);
            response.data = productService.createCategory(category);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Borra una categoria del sistema
     *
     * @param id Identificador de la categoria a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/category/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = productService.deleteCategory(id);
            if (response.data == null) {
                response.message = "La categoria no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La categoria fue removido exitosamente";
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
     * Index de subcategorias, responde con el listado de subcategorias
     *
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/subcategory")
    public ResponseEntity<Response> subcategory() {
        response.restart();
        try {
            response.data = productService.getListSubcategory();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva subcategoria en el sistema
     *
     * @param subcategory Objeto subcategoria a crear
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/api/v1/subcategory")
    public ResponseEntity<Response> createSubcategory(@RequestBody Subcategory subcategory) {
        response.restart();
        try {
            log.info("Subcategoria a crear: {}", subcategory);
            response.data = productService.createSubcategory(subcategory);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra una subcategoria del sistema
     *
     * @param id Identificador de la subcategoria a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Ricardo Ortega <tattortega.28@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/api/v1/subcategory/{id}")
    public ResponseEntity<Response> deleteSubcategory(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = productService.deleteSubcategory(id);
            if (response.data == null) {
                response.message = "La subcategoria no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La subcategoria fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }
}
