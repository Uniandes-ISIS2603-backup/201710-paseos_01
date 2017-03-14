/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author re.vega11
 */
@Stateless
public class OfertaLogic {
    
    @Inject
    private OfertaPersistence Opersistence;
    
    @Inject
    private UsuarioPersistence Upersistence;
    
    @Inject
    private PaseoPersistence Ppersistence;
    
    public OfertaEntity createOferta(OfertaEntity oferta)throws BusinessLogicException{
        if (oferta.getFecha() == null || oferta.getFecha().before(new Date()))
           throw new BusinessLogicException ("La oferta debe tener fecha y esta debe ser posterior a la fecha actual");
        if(oferta.getInscritos() != 0)
           throw new BusinessLogicException ("El numero de inscritos no puede ser diferente de cero");
        if(Ppersistence.find(oferta.getPaseo().getId()) == null)
            throw new BusinessLogicException ("El paseo no existe");
        if(Upersistence.find(oferta.getGuia().getId()) == null)
            throw new BusinessLogicException ("El paseo no existe");
        return Opersistence.create(oferta);
    }
    
    public List<OfertaEntity> getOfertas (){
        return Opersistence.findAll();
    }
    
    public OfertaEntity getOferta (Long id){
        return Opersistence.find(id);
    }
    
    public OfertaEntity updateOferta(OfertaEntity oferta) throws BusinessLogicException {
      if (oferta.getFecha().before(new Date()))
          throw new BusinessLogicException ("No se puede editar una oferta que ya pasó");
      return Opersistence.update(oferta);
    }
    
    public void deleteOferta (Long id){
        Opersistence.delete(id);
    }
}