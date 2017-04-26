package co.edu.uniandes.csw.paseos.test.persistence;

import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.persistence.VisitaPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class VisitaPersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(VisitaEntity.class.getPackage())
                .addPackage(VisitaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private VisitaPersistence VisitaPersistence;

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
        em.createQuery("delete from VisitaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<VisitaEntity> data = new ArrayList<VisitaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            VisitaEntity entity = factory.manufacturePojo(VisitaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Book.
     *
     * @generated
     */
    @Test
    public void createBookTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VisitaEntity newEntity = factory.manufacturePojo(VisitaEntity.class);
        VisitaEntity result = VisitaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        VisitaEntity entity = em.find(VisitaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
    }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getBooksTest() {
        List<VisitaEntity> list = VisitaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (VisitaEntity ent : list) {
            boolean found = false;
            for (VisitaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getBookTest() {
        VisitaEntity entity = data.get(0);
        VisitaEntity newEntity = VisitaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
    }

    /**
     * Prueba para eliminar un Book.
     *
     * @generated
     */
    @Test
    public void deleteBookTest() {
        VisitaEntity entity = data.get(0);
        VisitaPersistence.delete(entity.getId());
        VisitaEntity deleted = em.find(VisitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        VisitaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VisitaEntity newEntity = factory.manufacturePojo(VisitaEntity.class);
        newEntity.setId(entity.getId());

        VisitaPersistence.update(newEntity);

        VisitaEntity resp = em.find(VisitaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
    }
}
