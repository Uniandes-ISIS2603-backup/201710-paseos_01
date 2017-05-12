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
import java.util.Base64;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class FotoDTO 
{
    
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
    public FotoDTO() 
    {
        
    }
    
    /**
     * Constructor a partir de una entidad de foto
     * @param entity Entidad de foto
     */
    public FotoDTO(FotoEntity entity)
    {
        if(entity!=null)
        {
            id = entity.getId();
            valor = "data:image/"+entity.getFormato()+";base64,"+Base64.getEncoder().encodeToString(entity.getValor());
        }
    }

    public FotoEntity toEntity()
    {
        FotoEntity entity = new FotoEntity();
        entity.setId(id);
        try
        {
            entity.setValor(Base64.getDecoder().decode(valor.split(",")[1]));
            entity.setFormato(valor.split(",")[0].split("/")[1].split(";")[0]);
        }
        catch(Exception e)
        {
            entity.setValor(null);
            entity.setFormato(null);
        }
        return entity;
    }
    
    /**
     * Método que retorna el id de la foto
     * @return id de la foto
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Método que establece el id de la foto 
     * @param id nuevo de la foto
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * Método que retorna el valor codificado de la foto
     * @return valor codificado de la foto
     */
    public String getValor() 
    {
        return valor;
    }

    /**
     * Método que establece el valor codificado de la foto 
     * @param valor nuevo de la foto
     */
    public void setValor(String valor) 
    {
        this.valor = valor;
    }
    
    
}
