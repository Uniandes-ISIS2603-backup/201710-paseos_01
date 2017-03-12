/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
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
public class OfertaPersistence {
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public OfertaEntity find(Long id){        
        return em.find(OfertaEntity.class, id);
    }
    
    public List<OfertaEntity> findAll(){        
        Query q = em.createQuery("select u from OfertaEntity u");
        return q.getResultList();
    }
    
    public OfertaEntity create(OfertaEntity entity){
        em.persist(entity);
        return entity;
    }
    
    public OfertaEntity update(OfertaEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        OfertaEntity entity = em.find(OfertaEntity.class, id);
        em.remove(entity);
    }
}
