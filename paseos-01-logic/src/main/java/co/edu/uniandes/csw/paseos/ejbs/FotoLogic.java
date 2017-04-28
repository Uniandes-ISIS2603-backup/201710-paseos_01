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
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.persistence.FotoPersistence;
import co.edu.uniandes.csw.paseos.persistence.VisitaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class FotoLogic 
{
    
    @Inject 
    private FotoPersistence persistence;
    
    @Inject 
    private VisitaPersistence visitaPersistence;

    /**
     * Default constructor
     */
    public FotoLogic() 
    {
        
    }
    
    /**
     * Obtiene los datos de una instancia de Foto a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FotoEntity con los datos de la Foto consultada.
     */
    public FotoEntity getFotoVisita(Long id) 
    {
        return persistence.find(id);
    }
    
    /**
     * Obtiene los datos de una instancia de Foto a partir de su ID.
     * @param idVisita Identificador de la instancia a consultar
     * @return Instancia de FotoEntity con los datos de la Foto consultada.
     */
    public List<FotoEntity> getFotosVisita(Long idVisita) 
    {
        return persistence.findFotosVisita(idVisita);
    }

    /**
     * Se encarga de crear una Foto en la base de datos.
     * @param entity Objeto de FotoEntity con los datos nuevos
     * @param id id de la visita
     * @return Objeto de FotoEntity con los datos nuevos y su ID.
     * @generated
     */
    
    public FotoEntity createFotoVisita(FotoEntity entity, Long id) 
    {
        VisitaEntity visita = visitaPersistence.find(id);
        entity.setVisita(visita);
        persistence.create(entity);
        return entity;
    }

    /**
     * Elimina una instancia de Foto de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
   
    public void deleteFotoVisita(Long id) 
    {
        persistence.delete(id);
    }
}
