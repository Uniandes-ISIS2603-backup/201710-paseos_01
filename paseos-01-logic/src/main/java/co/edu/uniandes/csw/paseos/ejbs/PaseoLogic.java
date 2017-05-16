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
/**
 *
 * @author Venegas
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author tf.venegas10
 */
@Stateless
public class PaseoLogic {

    @Inject
    private PaseoPersistence persist;

    public PaseoEntity getPaseo(long id) {
        return persist.find(id);
    }

    public List<PaseoEntity> getPaseos() {
        return persist.findAll();
    }

    public PaseoEntity createPaseo(PaseoEntity paseo) throws BusinessLogicException {
        if (paseo == null) {
            throw new BusinessLogicException("El paseo no puede estar vacio");
        }
        if (paseo.getCosto() < 0) {
            throw new BusinessLogicException("EL costo del paseo debe ser positivo.");
        }
        if (paseo.getNumeroMaximo() < paseo.getNumeroMinimo() || paseo.getNumeroMinimo() < 0) {
            throw new BusinessLogicException("El número máximo de participantes debe ser mayor al número minimo y debe ser positivo.");
        }
        if (paseo.getCondicionFisica() < 0 || paseo.getCondicionFisica() > 10) {
            throw new BusinessLogicException("Condición física entre 0 y 10");
        }
        return persist.create(paseo);
    }

    public PaseoEntity modificar(PaseoEntity paseo) throws BusinessLogicException {
        if (paseo == null) {
            throw new BusinessLogicException("El paseo no puede estar vacio");
        }
        if (paseo.getId() == null) {
            throw new BusinessLogicException("El id del paseo no puede estar vacio");
        }
        PaseoEntity actual = persist.find(paseo.getId());
        if (actual == null) {
             throw new BusinessLogicException("El paseo a modificar no existe");
        }
        if (paseo.getCosto() < 0) {
            throw new BusinessLogicException("EL costo del paseo debe ser positivo.");
        }
        if (paseo.getNumeroMaximo() < paseo.getNumeroMinimo() || paseo.getNumeroMinimo() < 0) {
            throw new BusinessLogicException("El número máximo de participantes debe ser mayor al número minimo y debe ser positivo.");
        }
        if (paseo.getCondicionFisica() < 0 || paseo.getCondicionFisica() > 10) {
            throw new BusinessLogicException("Condición física entre 0 y 10");
        }
        return persist.update(paseo);
    }

    // Todo paseo se puede eliminar. Uno pasado, uno futuro. No hay restricciones de lógica de negocio 
    //para eliminar un paseo
    public void delete(long id) {
        persist.delete(id);
    }
}
