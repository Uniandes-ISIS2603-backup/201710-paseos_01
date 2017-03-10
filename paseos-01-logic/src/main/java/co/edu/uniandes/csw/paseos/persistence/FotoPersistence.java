/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public FotoEntity find(Long id){
        
        return em.find(FotoEntity.class, id);
        
    }
    
    public List<FotoEntity> findAll(){
        
        Query q = em.createQuery("select u from FotoEntity u");
        return q.getResultList();
    }
    
    public FotoEntity create(FotoEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    public FotoEntity update(FotoEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        FotoEntity entity = em.find(FotoEntity.class, id);
        em.remove(entity);
    }
}
