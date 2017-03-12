/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.paseos.persistence.VisitaPersistence;
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
    
    public List<VisitaEntity> getEntity() {
        return persistence.findAll();
    }
}
