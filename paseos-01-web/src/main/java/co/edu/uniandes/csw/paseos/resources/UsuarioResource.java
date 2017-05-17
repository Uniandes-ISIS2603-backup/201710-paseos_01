/* 
 * The MIT License
 *
 * Copyright 2017 jma.lovera10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.paseos.resources;



import co.edu.uniandes.csw.paseos.dtos.UsuarioDTO;
import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.paseos.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;



/**
 * URI: usuarios/
 * @generated
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class UsuarioResource {
    public UsuarioResource()
            {
                
            }
    
    @Inject 
    private UsuarioLogic ofertaLogic;
    
    /**
     * Convierte una lista de EmployeeEntity a una lista de EmployeeDetailDTO.
     *
     * @param entityList Lista de EmployeeEntity a convertir.
     * @return Lista de EmployeeDetailDTO convertida.
     * @generated
     */
    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList){
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
  /**
     * Obtiene la lista de los registros de Usuario
     *
     * @return Colección de objetos de UsuarioDetailDTO
     * @generated
     
    @GET

    @Path("usuarios")

    public List<UsuarioDetailDTO> getUsuarios() {
        
        return listEntity2DTO(ofertaLogic.getUsuarios());
    }  
    **/
    
     /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de UsuarioDetailDto con los datos del Usuario consultado
     * @generated
     */
    @GET
    @Path("/usuarios/{id: \\d+}")
    // TODO: retornar una excepción / código 404 si no existe
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        return new UsuarioDetailDTO(ofertaLogic.getUsuario(id));
    }

    /**
     * Se encarga de crear un Usuario en la base de datos
     *
     * @param dto Objeto de UsuarioDetailDTO con los datos nuevos
     * @return Objeto de UsuarioDetailDto con los datos nuevos y su ID
     * @generated
     */
    @POST
    @Path("usuarios")
    public UsuarioDTO createUsuario(UsuarioDTO dto) throws BusinessLogicException {
        return new UsuarioDTO(ofertaLogic.createUsuario(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Usuario
     *
     * @param id Identificador de la instancia de Usuario a modificar
     * @param dto Instancia de UsuarioDetailDTO con los nuevos datos
     * @return Instancia de UsuarioDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("usuarios/{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO dto) throws BusinessLogicException {
        UsuarioEntity entity = dto.toEntity();
        entity.setId(id);
        return new UsuarioDetailDTO(ofertaLogic.updateUsuario(entity));
    }

    /**
     * Elimina una instancia de Usuario de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE

    @Path("usuarios/{id: \\d+}")

    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        ofertaLogic.deleteUsuario(id);
    }
    
   @GET
   @Path("usuarios")
   public List<UsuarioDetailDTO> getUsuariosGuias(@QueryParam("guias")int g ){
      List<UsuarioDetailDTO> lista = new ArrayList<UsuarioDetailDTO>(); 
      List<UsuarioDetailDTO> lista1 = listEntity2DTO(ofertaLogic.getUsuarios());
      for (UsuarioDetailDTO usuario : lista1 )
      {
          if (usuario.getGuia()!=null)
             { 
          if (usuario.getGuia().booleanValue())
          {
              lista.add(usuario); 
          }
           }
      }
      
      if (g == 1 )
      {
          return lista; 
      }
      else 
      {
          return lista1; 
      }
   }
} 
