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

import co.edu.uniandes.csw.paseos.dtos.FotoDTO;
import co.edu.uniandes.csw.paseos.ejbs.FotoLogic;
import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotoResource 
{
    /**
     * Relación con la logica de foto
     */
    @Inject
    private FotoLogic fotoLogic;
    
    /**
     * Convierte una lista de EmployeeEntity a una lista de EmployeeDetailDTO.
     *
     * @param entityList Lista de EmployeeEntity a convertir.
     * @return Lista de EmployeeDetailDTO convertida.
     * @generated
     */
    private static List<FotoDTO> listEntity2DTO(List<FotoEntity> entityList)
    {
        List<FotoDTO> list = new ArrayList<>();
        for (FotoEntity entity : entityList) 
        {
            list.add(new FotoDTO(entity));
        }
        return list;
    }

    /**
     * Constructor por defecto de la clase
     */
    public FotoResource() 
    {
        
    }
    
    /**
     * Método que consulta todas las fotos
     * @param id de la foto
     * @return lista de fotos relacionadas con la visita
     */
    @GET
    @Path("fotos")
    public List<FotoDTO> getFotos()
    {
        return listEntity2DTO(fotoLogic.getFotos());
    }
    
    /**
     * Método que consulta todas las fotos dada una visita
     * @param id de la foto
     * @param idVisita de la visita
     * @return lista de fotos relacionadas con la visita
     */
    @GET
    @Path("visitas/{idVisita: \\d+}/fotos")
    public List<FotoDTO> getFotosVisita(@PathParam("idVisita") Long idVisita)
    {
        return listEntity2DTO(fotoLogic.getFotosVisita(idVisita));
    }
    
    /**
     * Método que obtiene una foto específica para un paseo
     * @param id de la foto
     * @return la foto relacionada con el id
     */
    @GET
    @Path("visitas/fotos/{id: \\d+}")
    public FotoDTO getFotoVisita(@PathParam("id") Long id)
    {
        return new FotoDTO(fotoLogic.getFotoVisita(id));
    }
    
    /**
     * Método que agrega una foto para una visita existente
     * @param idVisita de la visita
     * @param fotoDTO los datos de la foto
     * @return estatus de la operación
     * @throws BusinessLogicException si algo falla
     */
    @POST
    @Path("visitas/{idVisita: \\d+}/fotos")
    public FotoDTO addFotoVisita(@PathParam("idVisita") Long idVisita, FotoDTO fotoDTO)throws BusinessLogicException
    {
        return new FotoDTO(fotoLogic.createFotoVisita(fotoDTO.toEntity(),idVisita));
    }
   
    /**
     * Método para eliminar una foto dado su id
     * @param id de la foto a eliminar
     */
    @DELETE
    @Path("fotos/{id: \\d+}")
    public void deleteFotoVisita(@PathParam("id") Long id) 
    {
        fotoLogic.deleteFotoVisita(id);
    }
 }
