

package co.edu.uniandes.csw.paseos.resources;


import co.edu.uniandes.csw.paseos.dtos.PaseoDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.PaseoLogic;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import com.sun.org.glassfish.gmbal.ParameterNames;
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
public class PaseoResource {
    
    @Inject 
    private PaseoLogic logic;
    
    @GET
    public List<PaseoDetailDTO> getPaseos(@PathParam("catalogo") boolean cat){
        List<PaseoEntity> listEntities = logic.getPaseos();
        List<PaseoDetailDTO> lista= new ArrayList<PaseoDetailDTO>();
            for (PaseoEntity entity : listEntities) {
                lista.add(new PaseoDetailDTO(entity, cat));
            }
        return lista;
        
    }
    
    @GET
    @Path("{id : \\d+}")
    public PaseoDetailDTO getPaseo(@PathParam("id") long id){
        return new PaseoDetailDTO(logic.getPaseo(id),true);
        
    }
    
    @POST
    public PaseoDTO crearPaseo(PaseoDTO paso){
        
    }
          
    }