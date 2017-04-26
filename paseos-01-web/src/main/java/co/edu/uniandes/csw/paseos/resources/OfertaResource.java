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

import co.edu.uniandes.csw.paseos.dtos.OfertaDTO;
import co.edu.uniandes.csw.paseos.dtos.OfertaDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.OfertaLogic;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
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

@Path("/ofertas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfertaResource {
    
    @Inject    
    private OfertaLogic ofertaLogic;
    
    @GET
    public List <OfertaDTO> getOfertas(){
        List <OfertaDTO> ofertaDTOs = new ArrayList<>();
        List <OfertaEntity> ofertas = ofertaLogic.getOfertas();
        for(OfertaEntity oferta : ofertas){
            OfertaDTO dto = new OfertaDTO(oferta);
            ofertaDTOs.add(dto);
        }
        return ofertaDTOs;
    }
    
    @GET
    @Path("{id: \\d+}")
    public OfertaDetailDTO getOferta(@PathParam("id") Long id) throws BusinessLogicException{
        return new OfertaDetailDTO(ofertaLogic.getOferta(id));
    }
    
    @POST
    public OfertaDetailDTO addOferta(OfertaDetailDTO ofertaDTO)throws BusinessLogicException{
        OfertaEntity oferta = ofertaDTO.toEntity();
        OfertaEntity storedOferta = ofertaLogic.createOferta(oferta);
        return new OfertaDetailDTO(storedOferta);       
    }
    
    @PUT
    @Path("{id: \\d+}")
    public OfertaDetailDTO updateOferta(@PathParam("id") Long id, OfertaDetailDTO dto) throws BusinessLogicException {
        OfertaEntity entity = dto.toEntity();
        entity.setId(id);
        return new OfertaDetailDTO(ofertaLogic.updateOferta(entity));
    }
    
   @DELETE
    @Path("{id: \\d+}")
    public void deleteOferta(@PathParam("id") Long id) throws BusinessLogicException{
        ofertaLogic.deleteOferta(id);
    }
 }

