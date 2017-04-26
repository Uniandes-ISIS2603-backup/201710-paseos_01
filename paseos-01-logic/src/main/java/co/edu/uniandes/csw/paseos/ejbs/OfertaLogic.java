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
        if(Upersistence.find(oferta.getGuia().getId()) == null || !Upersistence.find(oferta.getGuia().getId()).getGuia())
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
