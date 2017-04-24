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
import org.junit.Before;
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
}
