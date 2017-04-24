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

