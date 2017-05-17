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
        fotos = new ArrayList<FotoDTO>();
        if(entity.getFotos()!=null||entity.getFotos().size()!=0){
            List<FotoEntity> temp = entity.getFotos();
            for(FotoEntity x: temp){
                FotoDTO fotoTemp = new FotoDTO(x);
                fotos.add(fotoTemp);
            }   
        }             
    }
    
    public VisitaEntity toEntity() {
        VisitaEntity entity = super.toEntity();
        if(fotos!=null){
            List<FotoEntity> temp = new ArrayList<FotoEntity>();
            if(fotos.size()!=0){
                for(FotoDTO x: fotos){
                    FotoEntity fotoTemp =x.toEntity();
                    temp.add(fotoTemp);
                }
            }            
            entity.setFotos(temp);
        }        
        return entity;
    }    
}
