/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.OfertaDTO;
import co.edu.uniandes.csw.paseos.ejbs.OfertaLogic;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jma.lovera10
 */
@Path("/ofertas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfertaResource {
    @Inject private OfertaLogic ofertaLogic;
    
    @GET
    public List<OfertaDTO> getOfertas(){
        List<OfertaDTO> list = new ArrayList<OfertaDTO>();
        
        return list;
    }
}
