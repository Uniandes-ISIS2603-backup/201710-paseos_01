
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
    
    public List<FotoEntity> findFotosVisita(Long id) {
        Query q = em.createQuery("select u from FotoEntity u where u.visita.id = "+id);
        return q.getResultList();
    }
    
    public FotoEntity findFotoVisita(Long idVisita , Long id) {
        Query q = em.createQuery("select u from FotoEntity u where u.id = "+id+" and u.visita.id = "+idVisita);
        List<FotoEntity> lista = q.getResultList();
        return lista.size()!=0?lista.get(0):null;
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
