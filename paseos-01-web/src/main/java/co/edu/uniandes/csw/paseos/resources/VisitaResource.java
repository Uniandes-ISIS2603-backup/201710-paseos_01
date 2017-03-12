/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.VisitaDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.VisitaLogic;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/visitas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitaResource {
    
    @Inject private VisitaLogic visitaLogic;
    
    private List<VisitaDetailDTO> listEntity2DTO(List<VisitaEntity> entityList){
        List<VisitaDetailDTO> list = new ArrayList<>();
        for (VisitaEntity entity: entityList) {
            list.add(new VisitaDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<VisitaDetailDTO> getVisitas() {
        return listEntity2DTO(visitaLogic.getVisita());
    }
    
    @GET
    @Path("{id: \\d+}")
    public VisitaDetailDTO getVisita(@PathParam("id") Long id){
        return new VisitaDetailDTO(visitaLogic.getVisita(id));
    }
    //TODO POST visita
    
    @PUT
    @Path("{id: \\d+}")
    public VisitaDetailDTO updateVisita(@PathParam("id") Long id, VisitaDetailDTO dto) throws BusinessLogicException{
        VisitaEntity entity = dto.toEntity();
        entity.setId(id);
        return new VisitaDetailDTO(visitaLogic.updateVisita(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVisita(@PathParam("id") Long id) throws BusinessLogicException{
        visitaLogic.deleteVisita(id);
    }
    
}
