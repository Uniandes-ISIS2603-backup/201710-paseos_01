/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    public UsuarioDetailDTO(){
        
    }
    public UsuarioDetailDTO(UsuarioEntity entity){
        
    }
    
    
}
