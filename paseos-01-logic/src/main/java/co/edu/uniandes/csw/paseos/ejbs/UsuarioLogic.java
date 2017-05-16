/* 
 * The MIT License
 *
 * Copyright 2017 jma.lovera10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.paseos.ejbs;

// TODO: eliminar los import que no se usan
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
     * @throws co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException
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
     * @throws co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException
     * @generated
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        // falta revisar si hay un usuario ya con ese login. 
        if (entity.getFechaNaciemiento().after(new Date()))
        {
            throw new BusinessLogicException("la fecha de nacimiento tiene que ser despues de la actual"); 
        }
        if (!persistence.loginUnico(entity.getLogin()))
        {
            throw new BusinessLogicException("ya existe un usuario con ese login"); 
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
    // TODO: revisar las validaciones al momento de actualizar
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException {
       
        UsuarioEntity ue = getUsuario(entity.getId());
        if (ue == null)
        {
            throw new BusinessLogicException("No hay un usuario con ese login"); 
        }
          
        if (persistence.loginUnico(entity.getLogin()))
        {
        return persistence.update(entity);
        }
        else 
        {
            throw new BusinessLogicException("Ya hay un usuario con ese login"); 
        }
    }
    
    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    // TODO: revisar las validaciones al momento de borrar
    public void deleteUsuario(Long id) throws BusinessLogicException {
        UsuarioEntity ue = getUsuario(id); 
        if (ue != null)
        {
        persistence.delete(id);
        }
        else 
        {
           throw new BusinessLogicException("No hay un usuario con ese login"); 
        }
    }
    
    
    
}
