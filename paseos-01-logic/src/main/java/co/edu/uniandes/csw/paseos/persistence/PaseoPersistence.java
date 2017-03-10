/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
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
public class PaseoPersistence {
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public PaseoEntity find(Long id){
        
        return em.find(PaseoEntity.class, id);
        
    }
    
    public List<PaseoEntity> findAll(){
        
        Query q = em.createQuery("select u from PaseoEntity u");
        return q.getResultList();
    }
    
    public PaseoEntity create(PaseoEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    public PaseoEntity update(PaseoEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        PaseoEntity entity = em.find(PaseoEntity.class, id);
        em.remove(entity);
    }
}
