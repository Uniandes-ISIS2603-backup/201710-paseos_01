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
        if(Upersistence.find(oferta.getGuia().getId()) == null) //|| !Upersistence.find(oferta.getGuia().getId()).getGuia())
            throw new BusinessLogicException ("El guía no existe");
        return Opersistence.create(oferta);
    }
    
    public List<OfertaEntity> getOfertas (){
        return Opersistence.findAll();
    }
    
    public OfertaEntity getOferta (Long id)throws BusinessLogicException{
        return Opersistence.find(id);
    }
    
    public OfertaEntity updateOferta(OfertaEntity oferta) throws BusinessLogicException {
      if (oferta.getFecha().before(new Date()))
          throw new BusinessLogicException ("No se puede editar una oferta que ya pasó");
      if(Upersistence.find(oferta.getGuia().getId()) == null || !Upersistence.find(oferta.getGuia().getId()).getGuia())
            throw new BusinessLogicException ("El guía no existe");
      return Opersistence.update(oferta);
    }
    
    public void deleteOferta (Long id) throws BusinessLogicException{
      if (Opersistence.find(id).getFecha().before(new Date()))
         throw new BusinessLogicException ("No se puede eliminar una oferta que ya pasó");
      Opersistence.delete(id);
    }
}
