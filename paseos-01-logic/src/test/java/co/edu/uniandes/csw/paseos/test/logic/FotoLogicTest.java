/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.test.logic;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.paseos.ejbs.FotoLogic;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jma.lovera10
 */
@RunWith(Arquillian.class)
public class FotoLogicTest {
     
    /**
     * Nombre del war
     */
    public static final String DEPLOY = "Prueba";
    
    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(FotoEntity.class.getPackage())
                .addPackage(FotoEntity.class.getPackage())
                .addPackage(FotoEntity.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private FotoLogic fotoLogic;
    
    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @generated
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
     * Prueba para consultar la lista de Fotos.
     *
     * @generated
     */
    @Test
    public void getBooksTest() {
        List<FotoEntity> list = fotoLogic.getFotos();
        Assert.assertEquals(data.size(), list.size());
        for (FotoEntity entity : list) {
            boolean found = false;
            for (FotoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Foto.
     *
     * @generated
     */
    @Test
    public void getBookTest() {
        FotoEntity entity = data.get(0);
        FotoEntity resultEntity = fotoLogic.getBook(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getIsbn(), resultEntity.getIsbn());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }
}
