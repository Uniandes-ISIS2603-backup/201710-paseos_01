/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class PaseoDetailDTO extends PaseoDTO{
    private List<OfertaDTO> ofertas;
    private List<FotoDTO> fotos ;

    public PaseoDetailDTO(PaseoEntity entity) {
        super(entity);
        List<OfertaEntity> ofertasEntities = entity.getOfertas();
        ofertas=new ArrayList<OfertaDTO>();
        for (OfertaEntity of : ofertasEntities) {
            ofertas.add(new OfertaDTO(of));
        }
                
        if (entity.getOfertas()!=null && entity.getOfertas().get(0)!=null && entity.getOfertas().get(0).getVisitas()!=null){
        this.fotos = FotoDTO( entity.getOfertas().get(0).getVisitas().get(0).getFotos());
        }
    }

    public List<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    public List<OfertaDTO> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<OfertaDTO> ofertas) {
        this.ofertas = ofertas;
    }
    public PaseoEntity toEntity(){
        PaseoEntity entity = new PaseoEntity();
        entity.setId(id);
        entity.setTematica(tematica);
        entity.setDestino(destino);
        entity.setCondicionFisica(condicionFisica);
        entity.setCosto(costo);
        entity.setTransporte(transporte);
        entity.setAlmuerzo(almuerzo);
        entity.setOfertas(ofertas);
        return entity;
    }
    
        
    
    
    
    
    
    
}
