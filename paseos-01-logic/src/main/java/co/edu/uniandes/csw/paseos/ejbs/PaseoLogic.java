
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
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
    
    public PaseoEntity createPaseo(PaseoEntity paseo) throws BusinessLogicException{
        if (paseo==null) throw new BusinessLogicException("El paseo no puede estar vacio");
        if (paseo.getCosto()<0) throw new BusinessLogicException("EL costo del paseo debe ser positivo.");
        if (paseo.getNumeroMaximo()<paseo.getNumeroMinimo() || paseo.getNumeroMinimo()<0) throw new BusinessLogicException("El número máximo de participantes debe ser mayor al número minimo y debe ser positivo.");
        if (paseo.getCondicionFisica()<0 || paseo.getCondicionFisica()>10) throw new BusinessLogicException("Condición física entre 0 y 10");
        return persist.create(paseo);
    }
    
    public PaseoEntity modificar(PaseoEntity paseo)throws BusinessLogicException{
        if (paseo==null) throw new BusinessLogicException("El paseo no puede estar vacio");
        if (paseo.getCosto()<0) throw new BusinessLogicException("EL costo del paseo debe ser positivo.");
        if (paseo.getNumeroMaximo()<paseo.getNumeroMinimo() || paseo.getNumeroMinimo()<0) throw new BusinessLogicException("El número máximo de participantes debe ser mayor al número minimo y debe ser positivo.");
        if (paseo.getCondicionFisica()<0 || paseo.getCondicionFisica()>10) throw new BusinessLogicException("Condición física entre 0 y 10");
        return persist.update(paseo);
    }
    
    // Todo paseo se puede eliminar. Uno pasado, uno futuro. No hay restricciones de lógica de negocio 
    //para eliminar un paseo
    public void delete (long id){
        persist.delete(id);
    }
}
