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
package co.edu.uniandes.csw.paseos.test.persistence;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Venegas
 */
@RunWith(Arquillian.class)
public class PaseosPersitenceTest {
    
    public static final String DEPLOY = "PruebaPaseoPersitence";
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(PaseoEntity.class.getPackage())
                .addClass(PaseoPersistence.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PaseoPersistence persitence;
    
    @PersistenceContext(unitName = "paseosPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PaseoEntity> data = new ArrayList<PaseoEntity>();
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData(10);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
 
     */
    private void clearData() {
        em.createQuery("delete from PaseoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData(int n) {
        for (int i = 0; i < n; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            PaseoEntity entity = factory.manufacturePojo(PaseoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /*
    Prueba la inserción de un paseo
    */
    @Test
    public void createPaseoTest(){
        PodamFactory factory = new PodamFactoryImpl();
        PaseoEntity paseo = factory.manufacturePojo(PaseoEntity.class);
        PaseoEntity result = persitence.create(paseo);
        assertNotNull(result);
        
        PaseoEntity entity = em.find(PaseoEntity.class, result.getId());
        
        assertEquals(result.getId(),entity.getId());
        
        assertEquals(paseo.getAlmuerzo(),entity.getAlmuerzo());
        assertEquals(paseo.getCondicionFisica(),entity.getCondicionFisica());
        assertEquals(paseo.getCosto(),entity.getCosto());
        assertEquals(paseo.getDescripcion(),entity.getDescripcion());
        assertEquals(paseo.getDestino(),entity.getDestino());
        assertEquals(paseo.getFotos(),entity.getFotos());
        assertEquals(paseo.getNumeroMaximo(),entity.getNumeroMaximo());
        assertEquals(paseo.getNumeroMinimo(),entity.getNumeroMinimo());
        assertEquals(paseo.getOfertas(),entity.getOfertas());
        assertEquals(paseo.getTematica(),entity.getTematica());
        assertEquals(paseo.getTransporte(),entity.getTransporte());
        
    }
    /*
    *Prueba para consultar todos los paseos
    */
    @Test
    public void getPaseosTest(){
        List<PaseoEntity> list = persitence.findAll();
        assertEquals(list.size(), data.size());
        
        for (PaseoEntity paseo : list){
            boolean found=false;
            for (PaseoEntity entity : data) {
                if (paseo.getId().equals(entity.getId())) {
                    found = true;           
                    assertEquals(paseo.getAlmuerzo(),entity.getAlmuerzo());
                    assertEquals(paseo.getCondicionFisica(),entity.getCondicionFisica());
                    assertEquals(paseo.getCosto(),entity.getCosto());
                    assertEquals(paseo.getDescripcion(),entity.getDescripcion());
                    assertEquals(paseo.getDestino(),entity.getDestino());
                    assertEquals(paseo.getFotos(),entity.getFotos());
                    assertEquals(paseo.getNumeroMaximo(),entity.getNumeroMaximo());
                    assertEquals(paseo.getNumeroMinimo(),entity.getNumeroMinimo());
                    assertEquals(paseo.getOfertas(),entity.getOfertas());
                    assertEquals(paseo.getTematica(),entity.getTematica());
                    assertEquals(paseo.getTransporte(),entity.getTransporte());
                }
            }
            assertTrue(found);
            
        }
    }
    
/**
     * Prueba para consultar la búsqueda de un Paseo
     *
     * 
     */
    @Test
    public void getBookTest() {
        PaseoEntity paseo = data.get(0);
        PaseoEntity entity = persitence.find(paseo.getId());
        assertNotNull(entity);
        
        assertEquals(paseo.getAlmuerzo(),entity.getAlmuerzo());
        assertEquals(paseo.getCondicionFisica(),entity.getCondicionFisica());
        assertEquals(paseo.getCosto(),entity.getCosto());
        assertEquals(paseo.getDescripcion(),entity.getDescripcion());
        assertEquals(paseo.getDestino(),entity.getDestino());
        assertEquals(paseo.getFotos(),entity.getFotos());
        assertEquals(paseo.getNumeroMaximo(),entity.getNumeroMaximo());
        assertEquals(paseo.getNumeroMinimo(),entity.getNumeroMinimo());
        assertEquals(paseo.getOfertas(),entity.getOfertas());
        assertEquals(paseo.getTematica(),entity.getTematica());
        assertEquals(paseo.getTransporte(),entity.getTransporte());
    }
    
      /**
     * Prueba para eliminar un Paseo
     *
     *
     */
    @Test
    public void deleteBookTest() {
        PaseoEntity entity = data.get(0);
        persitence.delete(entity.getId());
        PaseoEntity deleted = em.find(PaseoEntity.class, entity.getId());
        assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Paseo.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        PaseoEntity ent = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaseoEntity entity = factory.manufacturePojo(PaseoEntity.class);
        entity.setId(ent.getId());

        persitence.update(entity);

        PaseoEntity paseo = em.find(PaseoEntity.class, ent.getId());

        assertNotNull(paseo);
        
        assertEquals(paseo.getAlmuerzo(),entity.getAlmuerzo());
        assertEquals(paseo.getCondicionFisica(),entity.getCondicionFisica());
        assertEquals(paseo.getCosto(),entity.getCosto());
        assertEquals(paseo.getDescripcion(),entity.getDescripcion());
        assertEquals(paseo.getDestino(),entity.getDestino());
        assertEquals(paseo.getFotos(),entity.getFotos());
        assertEquals(paseo.getNumeroMaximo(),entity.getNumeroMaximo());
        assertEquals(paseo.getNumeroMinimo(),entity.getNumeroMinimo());
        assertEquals(paseo.getOfertas(),entity.getOfertas());
        assertEquals(paseo.getTematica(),entity.getTematica());
        assertEquals(paseo.getTransporte(),entity.getTransporte());
    }
       
    }
