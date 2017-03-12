/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class OfertaDetailDTO extends OfertaDTO{
    
    public OfertaDetailDTO(){
        super();
    }
    
    public OfertaDetailDTO(OfertaEntity entity){
        super(entity);
    }
    
    @Override
    public OfertaEntity toEntity() {
        OfertaEntity entity = super.toEntity();
        return entity;
    }
    
}
