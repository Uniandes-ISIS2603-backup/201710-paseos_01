/* 
 * The MIT License
 *
 * Copyright 2017 jma.lovera10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
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
