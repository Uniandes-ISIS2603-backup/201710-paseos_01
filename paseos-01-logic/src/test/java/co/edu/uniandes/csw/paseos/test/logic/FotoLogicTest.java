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
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.persistence.FotoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
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
                .addPackage(FotoPersistence.class.getPackage())
                .addPackage(FotoLogic.class.getPackage())
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
     * ConfiguraciÃ³n inicial de la prueba.
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
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que estÃ¡n implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from FotoEntity").executeUpdate();
        em.createQuery("delete from VisitaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<FotoEntity> data = new ArrayList<FotoEntity>();

    /**
     * @generated
     */
    private VisitaEntity visit = null;

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory fac = new PodamFactoryImpl();
        VisitaEntity vis = fac.manufacturePojo(VisitaEntity.class);
        em.persist(vis);
        visit = vis;
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            FotoEntity entity = factory.manufacturePojo(FotoEntity.class);
            entity.setVisita(visit);
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
        newEntity.setVisita(visit);
        newEntity.setFormato("jpeg");
        newEntity.setValor("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".getBytes());
        FotoEntity result = null;
        try {
            result = fotoLogic.createFotoVisita(newEntity, visit.getId());
        } catch (Exception e) {
            Assert.fail("No deberÃ­a fallar");
        }

        Assert.assertNotNull("El resultado no puede ser nulo", result);

        FotoEntity entity = em.find(FotoEntity.class, result.getId());

        Assert.assertEquals("El formato de la foto no coincide", newEntity.getFormato(), entity.getFormato());
        Assert.assertEquals("Los bytes de la imagen no coinciden", new String(newEntity.getValor()), new String(entity.getValor()));
        Assert.assertEquals("La referencia a la visita no coincide", newEntity.getVisita().getId(), entity.getVisita().getId());
    }

    /**
     * test para una ruta que es incorrecta
     */
    @Test
    public void createFotoFailTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FotoEntity newEntity = factory.manufacturePojo(FotoEntity.class);
        newEntity.setFormato(null);
        newEntity.setValor(null);
        newEntity.setVisita(visit);
        try {
            FotoEntity result = fotoLogic.createFotoVisita(newEntity, visit.getId());
            Assert.fail("DeberÃ­a fallar");
        } catch (Exception e) {

        }
    }

    /**
     * Prueba para consultar la lista de Fotos.
     *
     * @generated
     */
    @Test
    public void getFotosTest() {
        List<FotoEntity> list = fotoLogic.getFotosVisita(visit.getId());
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
    public void getFotoTest() {
        FotoEntity entity = data.get(0);
        FotoEntity newEntity = fotoLogic.getFotoVisita(entity.getId());
        Assert.assertNotNull("El resultado no puede ser nulo", newEntity);
        Assert.assertEquals("El formato de la foto no coincide", entity.getFormato(), newEntity.getFormato());
        Assert.assertEquals("Los bytes de la imagen no coinciden", new String(entity.getValor()), new String(newEntity.getValor()));
        Assert.assertEquals("La referencia a la visita no coincide", entity.getVisita().getId(), newEntity.getVisita().getId());
    }

    /**
     * Prueba para eliminar una Foto.
     *
     * @generated
     */
    @Test
    public void deleteFotoTest() {
        FotoEntity entity = data.get(0);
        fotoLogic.deleteFotoVisita(entity.getId());
        FotoEntity deleted = em.find(FotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
