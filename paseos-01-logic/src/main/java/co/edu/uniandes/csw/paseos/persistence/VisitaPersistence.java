/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jma.lovera10
 */
@Stateless
public class VisitaPersistence {
    
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
}
