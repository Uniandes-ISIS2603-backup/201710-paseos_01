package co.edu.uniandes.csw.paseos.test.logic;

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
import co.edu.uniandes.csw.paseos.ejbs.PaseoLogic;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import static co.edu.uniandes.csw.paseos.test.persistence.PaseosPersitenceTest.DEPLOY;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Venegas
 */

@RunWith(Arquillian.class)
public class PaseoLogictest {
     @Deployment
     public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(PaseoEntity.class.getPackage())
                .addPackage(PaseoLogic.class.getPackage())
                .addPackage(PaseoPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
     
     
    @Inject
    private PaseoPersistence persitence;
    
    @Inject
    private PaseoLogic logic;
    
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
        for (int i = 0; i < n; i++)
        {
            PodamFactory factory = new PodamFactoryImpl();
            PaseoEntity entity = factory.manufacturePojo(PaseoEntity.class);
            persitence.create(entity);
            data.add(entity);
        }
    }
    
       /*
    Prueba la inserción de un paseo
    */
    @Test
    public void createPaseoTest(){
         try {
             
             //success
             PodamFactory factory = new PodamFactoryImpl();
             PaseoEntity paseo = factory.manufacturePojo(PaseoEntity.class);
             paseo.setCosto(new Double(20000));
             paseo.setNumeroMinimo(10);
             paseo.setNumeroMaximo(100);
             paseo.setCondicionFisica(5);
             
             PaseoEntity result = logic.createPaseo(paseo);
             assertNotNull(result);
             
             PaseoEntity entity = persitence.find(result.getId());
             
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
             
             //success
             
             //null error
             try{
                logic.createPaseo(null);
                Assert.fail("Excepcion esperada  " );
             }
             catch(BusinessLogicException b){
                assertEquals(b.getMessage(), new BusinessLogicException("El paseo no puede estar vacio").getMessage());
             }
             
            //numeroMaximo < numeroMinimo 
             try{
                paseo.setNumeroMaximo(10);
                paseo.setNumeroMinimo(100);
                logic.createPaseo(paseo);
                Assert.fail("Excepcion esperada  " );
             }
             catch(BusinessLogicException b){

                 assertEquals(b.getMessage(), new BusinessLogicException("El número máximo de participantes debe ser mayor al número minimo y debe ser positivo.").getMessage());
             }
             
             //codicionFisica < 0  
             try{
                paseo.setNumeroMinimo(10);
                paseo.setNumeroMaximo(100);
                paseo.setCondicionFisica(-3);
                logic.createPaseo(paseo);
                Assert.fail("Excepcion esperada  " );
             }
             catch(BusinessLogicException b){
                 assertEquals(b.getMessage(), new BusinessLogicException("Condición física entre 0 y 10").getMessage());
             }
             
             //codicionFisica >10  
             try{
                paseo.setNumeroMinimo(10);
                paseo.setNumeroMaximo(100);
                paseo.setCondicionFisica(13);
                logic.createPaseo(paseo);
                Assert.fail("Excepcion esperada  " );
             }
             catch(BusinessLogicException b){
                 assertEquals(b.getMessage(), new BusinessLogicException("Condición física entre 0 y 10").getMessage());
             }
             
             //costo <0  
             try{
                paseo.setNumeroMinimo(10);
                paseo.setNumeroMaximo(100);
                paseo.setCondicionFisica(6);
                paseo.setCosto(new Double(-1000));
                logic.createPaseo(paseo);
                Assert.fail("Excepcion esperada  " );
             }
             catch(BusinessLogicException b){
                 assertEquals(b.getMessage(), new BusinessLogicException("EL costo del paseo debe ser positivo.").getMessage());
             }
             
         } catch (BusinessLogicException ex) {
             assertNull(ex);
         }
        
    }
    /*
    *Prueba para consultar todos los paseos
    */
    @Test
    public void getPaseosTest()
    {
        List<PaseoEntity> list = logic.getPaseos();
        assertEquals(list.size(), data.size());
        
        for (PaseoEntity paseo : list)
        {
            boolean found=false;
            for (PaseoEntity entity : data) 
            {
                if (paseo.getId().equals(entity.getId())) 
                {
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
    public void getPaseoTest() {
        PaseoEntity paseo = data.get(0);
        PaseoEntity entity =logic.getPaseo(paseo.getId());
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
        logic.delete(entity.getId());
        PaseoEntity deleted = persitence.find( entity.getId());
        assertNull(deleted);
    }
    
     
    
}
