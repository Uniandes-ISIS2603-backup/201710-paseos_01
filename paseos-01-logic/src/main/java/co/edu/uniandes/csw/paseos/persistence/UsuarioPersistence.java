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
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class UsuarioPersistence {
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public UsuarioEntity find(Long id){
        
        return em.find(UsuarioEntity.class, id);
        
    }
    
    public List<UsuarioEntity> findAll(){
        
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
    
    public UsuarioEntity create(UsuarioEntity entity){
        
        em.persist(entity);
        return entity;
    }
    
    public UsuarioEntity update(UsuarioEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
    public boolean loginUnico(String login)
    {
     boolean res = false; 
     Query q = em.createQuery("select u from UsuarioEntity u where u.login = :login").setParameter("login", login);
     if (q.getResultList().isEmpty())
     {
         res = true; 
     }
     return res;    
    }
}
