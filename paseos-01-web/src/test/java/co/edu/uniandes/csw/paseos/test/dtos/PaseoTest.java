package co.edu.uniandes.csw.paseos.test.dtos;

/* 
 * The MIT License
 *
 * Copyright 2017 tf.venegas10.
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
import co.edu.uniandes.csw.paseos.dtos.PaseoDetailDTO;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Venegas
 */

public class PaseoTest {

   
    @Test
    public void entityDtoentity() {
        PodamFactory factory = new PodamFactoryImpl();
        PaseoEntity paseo = factory.manufacturePojo(PaseoEntity.class);
        PaseoDetailDTO paseoDto = new PaseoDetailDTO(paseo);
        PaseoEntity entity = paseoDto.toEntity();
        assertEquals(paseo.getAlmuerzo(), entity.getAlmuerzo());
        assertEquals(paseo.getCondicionFisica(), entity.getCondicionFisica());
        assertEquals(paseo.getCosto(), entity.getCosto());
        assertEquals(paseo.getDescripcion(), entity.getDescripcion());
        assertEquals(paseo.getDestino(), entity.getDestino());
        assertEquals(paseo.getFotos(), entity.getFotos());
        assertEquals(paseo.getNumeroMaximo(), entity.getNumeroMaximo());
        assertEquals(paseo.getNumeroMinimo(), entity.getNumeroMinimo());
        assertEquals(paseo.getOfertas(), entity.getOfertas());
        assertEquals(paseo.getTematica(), entity.getTematica());
        assertEquals(paseo.getTransporte(), entity.getTransporte());

    }

    @Test
    public void DtoEntityDto() {
        PodamFactory factory = new PodamFactoryImpl();
        PaseoDetailDTO paseo = factory.manufacturePojo(PaseoDetailDTO.class);
        PaseoEntity paseoE = paseo.toEntity();
        PaseoDetailDTO entity = new PaseoDetailDTO(paseoE);
        entity.llenarListas(paseoE);
        
        assertEquals(paseo.getAlmuerzo(), entity.getAlmuerzo());
        assertEquals(paseo.getCondicionFisica(), entity.getCondicionFisica());
        assertEquals(paseo.getCosto(), entity.getCosto());
        assertEquals(paseo.getDescripcion(), entity.getDescripcion());
        assertEquals(paseo.getDestino(), entity.getDestino());
     
        assertEquals(paseo.getNumeroMaximo(), entity.getNumeroMaximo());
        assertEquals(paseo.getNumeroMinimo(), entity.getNumeroMinimo());
        assertEquals(paseo.getTematica(), entity.getTematica());
        assertEquals(paseo.getTransporte(), entity.getTransporte());
      
        assertEquals(paseo.getOfertas().size(), entity.getOfertas().size());
        


    }
}
