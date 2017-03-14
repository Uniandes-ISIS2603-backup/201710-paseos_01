/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.paseos.persistence.VisitaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class VisitaLogic {
    
    @Inject private VisitaPersistence persistenceVisita;
    
    @Inject private UsuarioPersistence persistenceUsuario;
    
    @Inject private OfertaPersistence persistenceOferta;
    
    public List<VisitaEntity> getVisita() {
        return persistenceVisita.findAll();
    }
    
    public VisitaEntity getVisita(Long id) {
        return persistenceVisita.find(id);
    } 
    
    public List<VisitaEntity> getVisitaPorUsuario(Long idUsusario){
        List<VisitaEntity> lista = persistenceVisita.findAll();
        List<VisitaEntity> respuesta = new ArrayList<VisitaEntity>();
        for(VisitaEntity x : lista){
            if(x.getUsuario().getId().compareTo(idUsusario)==0){
                respuesta.add(x);
            }
        }
        return respuesta;
    }
    
    public VisitaEntity updateVisita(VisitaEntity entity) throws BusinessLogicException{
        if(getVisita(entity.getId()).getOferta()!=entity.getOferta()){
            throw new BusinessLogicException("La oferta no es la oferta original");
        }
        if(!entity.getOferta().getFecha().before(new Date())){
            throw new BusinessLogicException("La fecha de oferta tiene que haber pasado");
        }
        if(getVisita(entity.getId()).getUsuario()!=entity.getUsuario()){
            throw new BusinessLogicException("El usuario no es el usuario original");
        }
        if(getVisita(entity.getId()).getCalificacion()!=entity.getCalificacion()){
            if(!getVisita(entity.getId()).getUsuario().getGuia()){
                throw new BusinessLogicException("El usuario que deberia ser guia no es guia");
            }
            UsuarioEntity temp = entity.getUsuario();
            temp.recalcularPromedio(entity.getCalificacion());
            persistenceUsuario.update(temp);
        }
        return persistenceVisita.update(entity);
    }
    
    public void deleteVisita(Long id) throws BusinessLogicException{
        if(getVisita(id).getOferta().getFecha().before(new Date(System.currentTimeMillis()))){
            throw new BusinessLogicException("La fecha de oferta no puede haber pasado");
        }
        
        UsuarioEntity update = getVisita(id).getUsuario();
        update.deleteVisita(getVisita(id));
        persistenceUsuario.update(update);
        
        OfertaEntity up= getVisita(id).getOferta();
        up.deleteVisita(getVisita(id));
        persistenceOferta.update(up);
        persistenceVisita.delete(id);
    }
    
    public VisitaEntity createVisita(VisitaEntity entity) throws BusinessLogicException{
        if(!getVisita(entity.getId()).getUsuario().getGuia()){
                throw new BusinessLogicException("El usuario que deberia ser guia no es guia");
        }
        if(entity.getOferta().getFecha().before(new Date())){
            throw new BusinessLogicException("La fecha de oferta no puede haber pasado");
        }
        UsuarioEntity update = entity.getUsuario();
        update.addVisita(entity);
        persistenceUsuario.update(update);
        
        OfertaEntity up= entity.getOferta();
        up.addtVisita(entity);
        persistenceOferta.update(up);
        
        persistenceVisita.create(entity);
        return entity;
    }

    public List<VisitaEntity> getVisitaPorPaseo(Long idPaseo) {
        List<VisitaEntity> lista = persistenceVisita.findAll();
        List<VisitaEntity> respuesta = new ArrayList<VisitaEntity>();
        for(VisitaEntity x : lista){
            if(x.getOferta().getPaseo().getId().compareTo(idPaseo)==0){
                respuesta.add(x);
            }
        }
        return respuesta;
    }
}