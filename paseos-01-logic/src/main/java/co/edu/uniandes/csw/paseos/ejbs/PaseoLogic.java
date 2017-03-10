/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class PaseoLogic {
    
    @Inject private PaseoPersistence presistence;
}
