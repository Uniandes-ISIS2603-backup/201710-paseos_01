/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class OfertaDTO {
    
    /**
     * Atributo que modela el id de la oferta
     */
    protected Long id;
    
    /**
     * Atributo que modela la fecha de la oferta
     */
    protected Date fecha;
    
    /**
     * Atributo que modela la cantidad de inscritos para la oferta
     */
    protected Integer inscritos;

    /**
     * Constructor por defecto
     */
    public OfertaDTO() {
    }
    
    public OfertaDTO(OfertaEntity entity){
        if(entity!=null){
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.inscritos = entity.getInscritos();
        }
    }
    
    public OfertaEntity toEntity(){
        OfertaEntity entity = new OfertaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setInscritos(inscritos);
        return entity;
    }

    /**
     * Obtiene el id de la oferta
     * @return id de la oferta
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id de la oferta
     * @param id de la oferta
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de la oferta
     * @return fecha de la oferta
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la oferta
     * @param fecha de la oferta
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene los inscritos de la oferta
     * @return inscritos de la oferta
     */
    public Integer getInscritos() {
        return inscritos;
    }

    /**
     * Establece los inscritos de la oferta
     * @param inscritos de la oferta
     */
    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }
    
    
}
