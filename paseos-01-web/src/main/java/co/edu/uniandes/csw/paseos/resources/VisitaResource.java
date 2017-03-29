// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.VisitaDetailDTO;
import co.edu.uniandes.csw.paseos.dtos.VisitaDTO;
import co.edu.uniandes.csw.paseos.ejbs.OfertaLogic;
import co.edu.uniandes.csw.paseos.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.paseos.ejbs.VisitaLogic;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
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
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitaResource {
    
    @Inject private VisitaLogic visitaLogic;
    
    @Inject private OfertaLogic ofertaLogic;
    
    @Inject private UsuarioLogic usuarioLogic;
    
    private List<VisitaDTO> listEntity2DTO(List<VisitaEntity> entityList){
        List<VisitaDTO> list = new ArrayList<>();
        for (VisitaEntity entity: entityList) {
            list.add(new VisitaDTO(entity));
        }
        return list;
    }
    
    @GET
    @Path("visitas")
    public List<VisitaDTO> getVisitas() {
        return listEntity2DTO(visitaLogic.getVisita());
    }
    
    @GET
    @Path("usuarios/{idUsuario: \\d+}/visitas")
    public List<VisitaDTO> getVisitasPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        return listEntity2DTO(visitaLogic.getVisitaPorUsuario(idUsuario));
    }
    
    @GET
    @Path("paseos/{idPaseo: \\d+}/visitas")
    public List<VisitaDTO> getVisitasPorPaseo(@PathParam("idPaseo") Long idPaseo) {
        return listEntity2DTO(visitaLogic.getVisitaPorPaseo(idPaseo));
    }
    
    @GET
    @Path("/visitas/{id: \\d+}")
    // TODO: retornar una excepción / código 404 si no existe
    public VisitaDetailDTO getVisita(@PathParam("id") Long id){
        return new VisitaDetailDTO(visitaLogic.getVisita(id));
    }
    
    @POST
    @Path("usuarios/{idUsuario: \\d+}/ofertas/{idOferta: \\d+}/visitas/{id: \\d+}")
    public VisitaDTO createVisita(@PathParam("id") Long id, @PathParam("idOferta") Long idOferta, @PathParam("idUsuario") Long idUsuario, VisitaDTO dto ) throws BusinessLogicException{
        VisitaEntity entity = dto.toEntity();
        entity.setId(id);
        OfertaEntity oferta = ofertaLogic.getOferta(idOferta);
        entity.setOferta(oferta);
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        entity.setUsuario(usuario);
        return new VisitaDTO(visitaLogic.createVisita(entity));
    }
    
    @PUT
    @Path("visitas/{id: \\d+}")
    public VisitaDTO updateVisita(@PathParam("id") Long id, VisitaDTO dto) throws BusinessLogicException{
        VisitaEntity entity = dto.toEntity();
        entity.setId(id);
        return new VisitaDTO(visitaLogic.updateVisita(entity));
    }
    
    @DELETE
    @Path("visitas/{id: \\d+}")
    public void deleteVisita(@PathParam("id") Long id) throws BusinessLogicException{
        visitaLogic.deleteVisita(id);
    }
    
}
