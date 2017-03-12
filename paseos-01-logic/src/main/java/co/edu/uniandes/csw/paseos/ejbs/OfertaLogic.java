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
 * @author jma.lovera10
 */
@Stateless
public class OfertaLogic {
    
    @Inject
    private OfertaPersistence persistence;
    
    public OfertaEntity createCompany(OfertaEntity oferta)throws BusinessLogicException{
        if (oferta.getFecha() == null || oferta.getFecha().after(new Date(System.currentTimeMillis())))
            throw new BusinessLogicException ("La oferta debe tener fecha y esta debe ser posterior a la fecha actual");
        if(oferta.getPaseo() == null)
           throw new BusinessLogicException ("La oferta debe tener pertenecer a un paseo");
        if(oferta.getInscritos() != 0)
           throw new BusinessLogicException ("El numero de inscritos no puede ser diferente de cero");
        if(oferta.getVisitas().size()!= 0)
            throw new BusinessLogicException ("La oferta se acaba de crear, no puede tener visitas");

        return persistence.create(oferta);
    }
    
    public List<OfertaEntity> getCompanies (){
        return persistence.findAll();
    }
    
    public OfertaEntity getCompany (Long id){
        return persistence.find(id);
    }
    
    public OfertaEntity updateCompany(OfertaEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteCompany (Long id){
        persistence.delete(id);
    }
}
