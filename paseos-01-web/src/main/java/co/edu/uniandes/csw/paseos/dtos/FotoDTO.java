/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class FotoDTO {
    
    /**
     * Atributo que modela el id de la foto
     */
    protected Long id;
    
    /**
     * Atributo que modela el valor codificado de la imagen
     */
    protected String valor;

    /**
     * Constructor por defecto
     */
    public FotoDTO() {
    }
    
    /**
     * Constructor a partir de una entidad de foto
     * @param entity Entidad de foto
     */
    public FotoDTO(FotoEntity entity){
        if(entity!=null){
            id = entity.getId();
            valor = entity.getValor();
        }
    }

    /**
     * Método que retorna el id de la foto
     * @return id de la foto
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que establece el id de la foto 
     * @param id nuevo de la foto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que retorna el valor codificado de la foto
     * @return valor codificado de la foto
     */
    public String getValor() {
        return valor;
    }

    /**
     * Método que establece el valor codificado de la foto 
     * @param valor nuevo de la foto
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
