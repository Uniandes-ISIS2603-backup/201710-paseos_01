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
    @Path("usuarios/{idUsuario: \\d+}/ofertas/{idOferta: \\d+}")
    public VisitaDTO createVisita(@PathParam("idOferta") Long idOferta, @PathParam("idUsuario") Long idUsuario, VisitaDTO dto ) throws BusinessLogicException{
        VisitaEntity entity = dto.toEntity();
        VisitaDTO visita = new VisitaDTO(visitaLogic.createVisita(entity));
        OfertaEntity oferta = ofertaLogic.getOferta(idOferta);
        entity.setOferta(oferta);
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        entity.setUsuario(usuario);
        visitaLogic.setUsuarioYOferta(entity);
        return visita;
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
