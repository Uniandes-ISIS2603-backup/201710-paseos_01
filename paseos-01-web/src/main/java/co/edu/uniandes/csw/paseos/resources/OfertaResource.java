
package co.edu.uniandes.csw.paseos.resources;

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

@Path("/paseos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfertaResource {
    
    @Inject
    private OfertaLogic ofertaLogic;
    
    //GET /habitaciones -- obtiene todas las Habitaciones
    @GET
    public List <OfertaDetailDTO> getOfertas(){
        List <OfertaDetailDTO> ofertaDTOs = new ArrayList<>();
        List <OfertaDetailDTO> ofertas = ofertaLogic.getOfertas();
        for(OfertaEntity oferta : ofertas){
            OfertaDetailDTO dto = new OfertaDetailDTO(oferta);
            ofertaDTOs.add(dto);
        }
        return habitacionDTOs;
    }
    
    //POST /companies -- agrega una habitacion
    @POST
    public HabitacionDTO addCompany(HabitacionDTO habitacionDTO)throws BusinessLogicException{
        HabitacionEntity habitacion = habitacionDTO.toEntity();
        HabitacionEntity storedHabitacion = habitacionLogic.createHabitacion(habitacion);
        return new HabitacionDTO(storedHabitacion);
    }
 }
