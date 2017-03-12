
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.ejbs.FotoLogic;
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

@Path("/fotos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotoResource {
    
    @Inject
    private FotoLogic habitacionLogic;
    
    //GET /habitaciones -- obtiene todas las Habitaciones
    @GET
    public List <HabitacionDTO> getCompanies(){
        List <HabitacionDTO> habitacionDTOs = new ArrayList<>();
        List <HabitacionEntity> habitaciones = habitacionLogic.getHabitaciones();
        for(HabitacionEntity habitacion : habitaciones){
            HabitacionDTO dto = new HabitacionDTO(habitacion);
            habitacionDTOs.add(dto);
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
