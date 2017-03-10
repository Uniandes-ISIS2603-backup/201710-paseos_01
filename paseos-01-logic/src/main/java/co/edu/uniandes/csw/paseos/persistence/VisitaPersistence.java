/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
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
public class VisitaPersistence {
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public VisitaEntity find(Long id){
        
        return em.find(VisitaEntity.class, id);
        
    }
    
    public List<VisitaEntity> findAll(){
        
        Query q = em.createQuery("select u from VisitaEntity u");
        return q.getResultList();
    }
    
    public VisitaEntity create(VisitaEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    public VisitaEntity update(VisitaEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        VisitaEntity entity = em.find(VisitaEntity.class, id);
        em.remove(entity);
    }
}
