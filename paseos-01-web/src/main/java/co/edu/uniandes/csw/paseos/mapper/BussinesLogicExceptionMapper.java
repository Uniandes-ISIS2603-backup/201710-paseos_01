package co.edu.uniandes.csw.paseos.mapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author s.cortes
 */
@Provider
public class BussinesLogicExceptionMapper implements ExceptionMapper<BusinessLogicException>
{ 
    /**
* Generador de una respuesta a partir de una excepción
* @param ex excecpión a convertir a una respuesta REST
*/
@Override
public Response toResponse(BusinessLogicException ex) {
// retorna una respuesta
return Response
.status(Response.Status.NOT_FOUND) // estado HTTP 404
.entity(ex.getMessage()) // mensaje adicional
.type("text/plain")
.build();
}

}
