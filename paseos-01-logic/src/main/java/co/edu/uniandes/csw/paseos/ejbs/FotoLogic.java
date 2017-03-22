// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
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
public class FotoLogic {
    
    @Inject private FotoPersistence persistence;
    
    @Inject private VisitaPersistence visitaPersistence;
    
    /**
     * Obtiene la lista de los registros de Foto para una visita.
     * @return Colección de objetos de FotoEntity.
     */
   
    public List<FotoEntity> getFotos() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Foto a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FotoEntity con los datos de la Foto consultada.
     */
    public FotoEntity getFoto(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Obtiene los datos de una instancia de Foto a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FotoEntity con los datos de la Foto consultada.
     */
    public FotoEntity getFotoVisita(Long id,Long idVisita) {
        return persistence.findFotoVisita(idVisita, id );
    }
    
    /**
     * Obtiene los datos de una instancia de Foto a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FotoEntity con los datos de la Foto consultada.
     */
    public List<FotoEntity> getFotosVisita(Long idVisita) {
        return persistence.findFotosVisita(idVisita);
    }

    /**
     * Se encarga de crear una Foto en la base de datos.
     * @param entity Objeto de FotoEntity con los datos nuevos
     * @return Objeto de FotoEntity con los datos nuevos y su ID.
     * @generated
     */
    
    public FotoEntity createFotoVisita(FotoEntity entity, Long id) {
        visitaPersistence.createFoto(entity, id);
        return entity;
    }

    /**
     * Elimina una instancia de Foto de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
   
    public void deleteFotoVisita(Long id) {
        persistence.delete(id);
    }
}
