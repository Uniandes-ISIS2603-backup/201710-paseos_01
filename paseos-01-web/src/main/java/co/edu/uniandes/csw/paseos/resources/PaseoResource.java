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


import co.edu.uniandes.csw.paseos.dtos.PaseoDTO;
import co.edu.uniandes.csw.paseos.dtos.PaseoDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.PaseoLogic;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class PaseoResource {
    
    @Inject 
    private PaseoLogic logic;
   
    
    @GET
    @Path("paseos")
    public List<PaseoDTO> getPaseos( @QueryParam("catalogo")int cat ){
        List<PaseoEntity> listEntities = logic.getPaseos();
        List<PaseoDTO> lista= new ArrayList<PaseoDTO>();
            for (PaseoEntity entity : listEntities) {
                //.llenarisaje()
                if (cat==1)
                {
                    PaseoDetailDTO p=new PaseoDetailDTO(entity);
                    p.llenarListas(entity);
                    lista.add(p);
                }
                else if (cat==0)
                {
                lista.add(new PaseoDTO(entity));
                }
                
                
                    //
//cat!=0));
            }
        return lista;
        
    }
    
    @GET
    @Path("/paseos/{id : \\d+}")
    // TODO: retornar una excepción / código 404 si no existe
    public PaseoDetailDTO getPaseo(@PathParam("id") long id){
        return new PaseoDetailDTO(logic.getPaseo(id));
        
    }
    
    @POST
    @Path("/paseos")
    public PaseoDTO crearPaseo(PaseoDTO paseo) throws BusinessLogicException{
        if (paseo==null)
        {
            throw new BusinessLogicException();
        }
        return new PaseoDTO(logic.createPaseo(paseo.toEntity()));
    }
    
    @PUT
    @Path("/paseos/{id: \\d+}")
    public PaseoDTO modificarPaseo(PaseoDTO paseo, @PathParam("id") long id ) throws BusinessLogicException{
       PaseoEntity entity = paseo.toEntity();
        entity.setId(id);
        return new PaseoDTO(logic.modificar(entity));
    }
    
    @DELETE
    @Path("/paseos/{id: \\d+}")
    public void deletePaseo(@PathParam("id") long id) {
        logic.delete(id);
    }
    }
