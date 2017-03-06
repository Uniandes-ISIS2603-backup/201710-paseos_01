/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class VisitaDTO {

    /**
     * Atributo que modela el id generado de la visita
     */
    protected Long id;
    
    /**
     * Atributo que modela la calificacion de la visita
     */
    protected Integer calificacion;
    
    /**
     * Atributo que modela el comentario acerca de la visita
     */
    protected String comentario;
    
    /**
     * Constructor por defecto
     */
    public VisitaDTO() {
    }
    
    public VisitaDTO(VisitaEntity entity){
        if(entity!=null){
            this.id = entity.getId();
            this.calificacion = entity.getCalificacion();
            this.comentario = entity.getComentario();
        }
    }
    
    public VisitaEntity toEntity(){
        VisitaEntity entity = new VisitaEntity();
        entity.setId(id);
        entity.setCalificacion(calificacion);
        entity.setComentario(comentario);
        return entity;
    }

    /**
     * Obtiene el id de la visita
     * @return id de la visita
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id de la visita
     * @param id de la visita
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la calificacion de la visita
     * @return calificacion de la visita
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Establece la calificacion de la visita
     * @param calificacion de la visita
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el comentario de la visita
     * @return comentario de la visita
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario de la visita
     * @param comentario de la visita
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
