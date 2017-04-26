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

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class OfertaDTO {
    
    
    protected Long id;
    
   
    protected Date fecha;
    
    
    protected Integer inscritos;
    
    
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
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setInscritos(this.getInscritos());
        return entity;
    }

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Date getFecha() {
        return fecha;
    }

    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    public Integer getInscritos() {
        return inscritos;
    }

  
    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }
}
