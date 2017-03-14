/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class OfertaDetailDTO extends OfertaDTO{
    
    private List <VisitaDTO> visitas;
    
    public OfertaDetailDTO(){
        super();
    }
    public OfertaDetailDTO(OfertaEntity entity){
        super(entity);
        List <VisitaEntity> visitasEntities = entity.getVisitas();
        visitas = new ArrayList <VisitaDTO> ();
        for(VisitaEntity vi : visitasEntities){
            visitas.add(new VisitaDTO(vi));
        }
    }
    
     public List<VisitaDTO> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaDTO> visitas) {
        this.visitas = visitas;
    }
    
    @Override
    public OfertaEntity toEntity(){
        OfertaEntity entity = new OfertaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setGuia(guia.toEntity());
        entity.setInscritos(inscritos);
        ArrayList<VisitaEntity> list = new ArrayList<VisitaEntity>();
        for (VisitaDTO visitaDTO : visitas) {
            list.add(visitaDTO.toEntity());
        }
        entity.setVisitas(list);
        return entity;
    }
} 

