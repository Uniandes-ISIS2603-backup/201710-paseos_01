
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

@Path("/fotos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotoResource {
    
    @Inject
    private FotoLogic fotoLogic;
    
    /**
     * Convierte una lista de EmployeeEntity a una lista de EmployeeDetailDTO.
     *
     * @param entityList Lista de EmployeeEntity a convertir.
     * @return Lista de EmployeeDetailDTO convertida.
     * @generated
     */
    private List<FotoDTO> listEntity2DTO(List<FotoEntity> entityList){
        List<FotoDTO> list = new ArrayList<>();
        for (FotoEntity entity : entityList) {
            list.add(new FotoDTO(entity));
        }
        return list;
    }
    
    @GET
    public List <FotoDTO> getFotos(){
        return listEntity2DTO(fotoLogic.getFotos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public FotoDTO getFotos(@PathParam("id") Long id){
        return new FotoDTO(fotoLogic.getFoto(id));
    }
    
    //POST /companies -- agrega una habitacion
    @POST
    public FotoDTO addFoto(FotoDTO fotoDTO)throws BusinessLogicException{
        return new FotoDTO(fotoLogic.createFoto(fotoDTO.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEmployee(@PathParam("id") Long id) {
        fotoLogic.deleteFoto(id);
    }
 }
