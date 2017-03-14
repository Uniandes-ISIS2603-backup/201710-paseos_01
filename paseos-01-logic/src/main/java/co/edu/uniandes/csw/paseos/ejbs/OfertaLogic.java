/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
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
    private OfertaPersistence persistence;
    
    public OfertaEntity createOferta(OfertaEntity oferta)throws BusinessLogicException{
        if (oferta.getFecha() == null || oferta.getFecha().before(new Date()))
           throw new BusinessLogicException ("La oferta debe tener fecha y esta debe ser posterior a la fecha actual");
        if(oferta.getInscritos() != 0)
           throw new BusinessLogicException ("El numero de inscritos no puede ser diferente de cero");
        if(oferta.getVisitas().size()!= 0)
            throw new BusinessLogicException ("La oferta se acaba de crear, no puede tener visitas");
        if(oferta.getGuia() == null)
            throw new BusinessLogicException ("La oferta no se puede crear sin un guía");
        if(oferta.getPaseo() == null)
           throw new BusinessLogicException ("La oferta debe pertenecer a un paseo");
        return persistence.create(oferta);
    }
    
    public List<OfertaEntity> getOfertas (){
        return persistence.findAll();
    }
    
    public OfertaEntity getOferta (Long id){
        return persistence.find(id);
    }
    
    public OfertaEntity updateOferta(OfertaEntity oferta) throws BusinessLogicException {
      if (oferta.getFecha().after(new Date(System.currentTimeMillis())))
          throw new BusinessLogicException ("No se puede editar una oferta que ya pasó");
      return persistence.update(oferta);
    }
    
    public void deleteOferta (Long id){
        persistence.delete(id);
    }
}