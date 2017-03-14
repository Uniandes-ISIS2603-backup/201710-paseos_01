/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.Date;
import java.util.List;


/**
 *
 * @author n.acevedos
 */
@Stateless
public class UsuarioLogic {
    
    @Inject private UsuarioPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de Usuario.
     *
     * @return Colección de objetos de UsuarioEntity.
     * 
     */
   
    public List<UsuarioEntity> getUsuarios() {
        return persistence.findAll(); 
    }
    
    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     *
     */
    public UsuarioEntity getUsuario(Long id) throws BusinessLogicException {
        UsuarioEntity ue= persistence.find(id);
         if (ue == null)
         {
             throw new BusinessLogicException("No existe un usuario con ese id"); 
         }
         return ue; 
    }
    
    /**
     * Se encarga de crear un Usuario en la base de datos.
     *
     * @param entity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     * @generated
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        // falta revisar si hay un usuario ya con ese login. 
        if (entity.getFechaNaciemiento().after(new Date()))
        {
            throw new BusinessLogicException("la fecha de nacimiento tiene que ser despues de la actual"); 
        }
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza la información de una instancia de Usuario.
     *
     * @param entity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de UsuarioEntity con los datos actualizados.
     * 
     */
   
    public UsuarioEntity updateUsuario(UsuarioEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteUsuario(Long id) {
        persistence.delete(id);
    }
    
    
    
}
