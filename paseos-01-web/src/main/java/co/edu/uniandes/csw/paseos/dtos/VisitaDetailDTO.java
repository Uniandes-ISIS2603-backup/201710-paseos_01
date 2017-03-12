/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class VisitaDetailDTO extends VisitaDTO{
    
    private List<FotoDTO> fotos;
    
    public VisitaDetailDTO() {
        super();
    }
    
    public VisitaDetailDTO(VisitaEntity entity) {
        super(entity);
        List<FotoEntity> temp = entity.getFotos();
        for(FotoEntity x: temp){
            FotoDTO fotoTemp = new FotoDTO(x);
            fotos.add(fotoTemp);
        }        
    }
    
    public VisitaEntity toEntity() {
        VisitaEntity entity = super.toEntity();
        List<FotoEntity> temp = new ArrayList<FotoEntity>();
        for(FotoDTO x: fotos){
            FotoEntity fotoTemp =x.toEntity();
            temp.add(fotoTemp);
        }
        entity.setFotos(temp);
        return entity;
    }
    
}
