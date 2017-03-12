/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author tf.venegas10
 */
@Stateless
public class PaseoLogic {
    
    @Inject private PaseoPersistence persist;
    
    public PaseoEntity getPaseo(long id){
        return persist.find(id);
    }
    
    public List<PaseoEntity> getPaseos(){
        return persist.findAll();
    }
    
    public PaseoEntity createPaseo(PaseoEntity paseo){
        return persist.create(paseo);
    }
    
    public PaseoEntity modificar(PaseoEntity paseo){
        return persist.update(paseo);
    }
    
    public void delete (long id){
        persist.delete(id);
    }
}
