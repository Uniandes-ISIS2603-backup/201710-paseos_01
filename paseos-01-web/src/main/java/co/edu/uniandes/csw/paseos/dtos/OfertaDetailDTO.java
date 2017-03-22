// TODO: eliminar los comentarios por defecto
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
    
    private UsuarioDTO guia;
    
    private PaseoDTO paseo;
    
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
        guia = new UsuarioDTO(entity.getGuia());
        paseo = new PaseoDTO(entity.getPaseo());
    }
    
     public List<VisitaDTO> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaDTO> visitas) {
        this.visitas = visitas;
    }
    
    public UsuarioDTO getGuia(){
        return guia;
    }
    
    public void setGuia (UsuarioDTO guia){
        this.guia = guia;
    }
    
    public PaseoDTO getPaseo(){
        return paseo;
    }
    
    public void setPaseo (PaseoDTO paseo){
        this.paseo = paseo;
    }
    
    @Override
    public OfertaEntity toEntity(){
        OfertaEntity entity = new OfertaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setGuia(guia.toEntity());
        entity.setPaseo(paseo.toEntity());
        entity.setInscritos(inscritos);
        ArrayList<VisitaEntity> list = new ArrayList<VisitaEntity>();
        for (VisitaDTO visitaDTO : visitas) {
            list.add(visitaDTO.toEntity());
        }
        entity.setVisitas(list);
        return entity;
    }
} 

