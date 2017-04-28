package co.edu.uniandes.csw.paseos.test.persistence;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
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
public class OfertasPersistenceTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(OfertaEntity.class.getPackage())
                .addPackage(OfertaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private OfertaPersistence ofertaPersistence;

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
        em.createQuery("delete from OfertaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<OfertaEntity> data = new ArrayList<OfertaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            OfertaEntity entity = factory.manufacturePojo(OfertaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Oferta.
     *
     * @generated
     */
    @Test
    public void createOfertaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity newEntity = factory.manufacturePojo(OfertaEntity.class);
        OfertaEntity result = ofertaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        OfertaEntity entity = em.find(OfertaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFecha().getDay(), entity.getFecha().getDay());
        Assert.assertEquals(newEntity.getFecha().getMonth(), entity.getFecha().getMonth());
        Assert.assertEquals(newEntity.getFecha().getYear(), entity.getFecha().getYear());
        Assert.assertEquals(newEntity.getGuia(), entity.getGuia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getInscritos(), entity.getInscritos());
        Assert.assertEquals(newEntity.getPaseo(), entity.getPaseo());
        Assert.assertEquals(newEntity.getVisitas(), entity.getVisitas());
    }

    /**
     * Prueba para consultar la lista de Ofertas.
     *
     * @generated
     */
    @Test
    public void getOfertasTest() {
        List<OfertaEntity> list = ofertaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OfertaEntity ent : list) {
            boolean found = false;
            for (OfertaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Oferta.
     *
     * @generated
     */
    @Test
    public void getOfertaTest() {
        OfertaEntity entity = data.get(0);
        OfertaEntity newEntity = ofertaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFecha().getDay(), entity.getFecha().getDay());
        Assert.assertEquals(newEntity.getFecha().getMonth(), entity.getFecha().getMonth());
        Assert.assertEquals(newEntity.getFecha().getYear(), entity.getFecha().getYear());
        Assert.assertEquals(newEntity.getGuia(), entity.getGuia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getInscritos(), entity.getInscritos());
        Assert.assertEquals(newEntity.getPaseo(), entity.getPaseo());
        Assert.assertEquals(newEntity.getVisitas(), entity.getVisitas());
    }

    /**
     * Prueba para eliminar un Oferta.
     *
     * @generated
     */
    @Test
    public void deleteOfertaTest() {
        OfertaEntity entity = data.get(0);
        ofertaPersistence.delete(entity.getId());
        OfertaEntity deleted = em.find(OfertaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Oferta.
     *
     * @generated
     */
    @Test
    public void updateOfertaTest() {
        OfertaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity newEntity = factory.manufacturePojo(OfertaEntity.class);
        newEntity.setId(entity.getId());

        ofertaPersistence.update(newEntity);

        OfertaEntity resp = em.find(OfertaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFecha().getDay(), entity.getFecha().getDay());
        Assert.assertEquals(newEntity.getFecha().getMonth(), entity.getFecha().getMonth());
        Assert.assertEquals(newEntity.getFecha().getYear(), entity.getFecha().getYear());
        Assert.assertEquals(newEntity.getGuia(), entity.getGuia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getInscritos(), entity.getInscritos());
        Assert.assertEquals(newEntity.getPaseo(), entity.getPaseo());
        Assert.assertEquals(newEntity.getVisitas(), entity.getVisitas());
    }
}
