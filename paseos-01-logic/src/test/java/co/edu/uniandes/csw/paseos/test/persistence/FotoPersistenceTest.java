/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.test.persistence;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import co.edu.uniandes.csw.paseos.persistence.FotoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jma.lovera10
 */
@RunWith(Arquillian.class)
public class FotoPersistenceTest {
    
    /**
     * 
     */
    public static final String DEPLOY = "Prueba";
    
    /**
     * 
     * @return 
     */
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FotoEntity.class.getPackage())
                .addPackage(FotoPersistenceTest.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * La persistencia de la foto
     */
    @Inject
    private FotoPersistence fotoPersistence;
    
    /**
     * Manejador de entidades
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * User transaction
     */
    @Inject
    UserTransaction utx;
    
     /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from FotoEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<FotoEntity> data = new ArrayList<FotoEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FotoEntity entity = factory.manufacturePojo(FotoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Foto.
     *
     * @generated
     */
    @Test
    public void createFotoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FotoEntity newEntity = factory.manufacturePojo(FotoEntity.class);
        FotoEntity result = fotoPersistence.create(newEntity);

        Assert.assertNotNull("El resultado no puede ser nulo",result);

        FotoEntity entity = em.find(FotoEntity.class, result.getId());

        Assert.assertEquals("El formato de la foto no coincide",newEntity.getFormato(), entity.getFormato());
        Assert.assertEquals("Los bytes de la imagen no coinciden",new String(newEntity.getValor()), new String(entity.getValor()));
        Assert.assertEquals("La referencia a la visita no coincide",newEntity.getVisita().getId(), entity.getVisita().getId());
    }
    
    /**
     * Prueba para consultar la lista de Fotos.
     *
     * @generated
     */
    @Test
    public void getFotosTest() {
        List<FotoEntity> list = fotoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FotoEntity ent : list) {
            boolean found = false;
            for (FotoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue("No se esta obteniendo todas las fotos de la bd",found);
        }
    }
    
     /**
     * Prueba para consultar una Foto.
     *
     * @generated
     */
    @Test
    public void getFotoTest() {
        FotoEntity entity = data.get(0);
        FotoEntity newEntity = fotoPersistence.find(entity.getId());
        Assert.assertNotNull("El resultado no puede ser nulo",newEntity);
        Assert.assertEquals("El formato de la foto no coincide",entity.getFormato(), newEntity.getFormato());
        Assert.assertEquals("Los bytes de la imagen no coinciden",new String(entity.getValor()), new String(newEntity.getValor()));
        Assert.assertEquals("La referencia a la visita no coincide",entity.getVisita().getId(), newEntity.getVisita().getId());
    }
    
    /**
     * Prueba para eliminar una Foto.
     *
     * @generated
     */
    @Test
    public void deleteFotoTest() {
        FotoEntity entity = data.get(0);
        fotoPersistence.delete(entity.getId());
        FotoEntity deleted = em.find(FotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
