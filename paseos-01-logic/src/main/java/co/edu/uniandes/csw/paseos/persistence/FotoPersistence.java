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
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class FotoPersistence {
    
    /**
     * Administrador de entidades
     */
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    /**
     * Método que encuentra la foto en la BD
     * @param id de la foto a buscar
     * @return la entidad de la foto
     */
    public FotoEntity find(Long id){
        
        return em.find(FotoEntity.class, id);
        
    }
    
    /**
     * Método que retorna todas las fotos
     * @return Fotos en el a BD
     */
    public List<FotoEntity> findAll(){
        
        Query q = em.createQuery("select u from FotoEntity u");
        return q.getResultList();
    }
    
    /**
     * Método que encuentra las fotos para una visita específica
     * @param id de la visita
     * @return Lista de las fotos asociadas a la visita
     */
    public List<FotoEntity> findFotosVisita(Long id) {
        Query q = em.createQuery("select u from FotoEntity u where u.visita.id = "+id);
        return q.getResultList();
    }
    
    /**
     * Método que crea una una entidad de foto
     * @param entity foto a crear
     * @return la foto creada
     */
    public FotoEntity create(FotoEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    /**
     * Método que actualiza una foto
     * @param entity foto a modificar
     * @return la foto modificada
     */
    public FotoEntity update(FotoEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Método que elimina una foto de la BD
     * @param id de la foto a eliminar
     */
    public void delete(Long id){
        em.remove(find(id));
    }

    

}
