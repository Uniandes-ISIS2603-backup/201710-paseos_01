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
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jma.lovera10
 */
@Entity
public class FotoEntity implements Serializable 
{
    
    /**
     * Id único de la foto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    /**
     * Valor codificado de la foto
     */
    private byte[] valor;
    
    /**
     * Formato de la foto
     */
    private String formato;
    
    /**
     * Relación con la visita
     */
    @ManyToOne
    @PodamExclude
    private VisitaEntity visita;

    /**
     * Método que obtiene el id
     * @return id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Método que establece el id de la foto
     * @param id de la foto
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * Método que obtiene el valor codificado
     * @return valor de la foto
     */
    public byte[] getValor() 
    {
        return valor;
    }

    /**
     * Método que establece el valor codificado de la foto
     * @param valor de la foto
     */
    public void setValor(byte[] valor) 
    {
        this.valor = valor;
    }

    /**
     * Método que obtiene el formato de la foto
     * @return formato de la foto
     */
    public String getFormato() 
    {
        return formato;
    }

    /**
     * Método que establece el formato de la foto
     * @param formato formato de la foto
     */
    public void setFormato(String formato) 
    {
        this.formato = formato;
    }

    /**
     * Método que obtiene la visita de la foto
     * @return visita de la foto
     */
    public VisitaEntity getVisita() 
    {
        return visita;
    }

    /**
     * Método que establece la visita de la foto
     * @param visita visita de la foto
     */
    public void setVisita(VisitaEntity visita) 
    {
        this.visita = visita;
    }
    
}
