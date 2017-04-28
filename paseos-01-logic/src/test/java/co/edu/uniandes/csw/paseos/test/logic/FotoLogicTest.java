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
     * Limpia las tablas que están implicadas en la prueba.
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
        FotoEntity result = fotoLogic.createFotoVisita(newEntity,visit.getId());

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
        fotoLogic.deleteFotoVisita(entity.getId());
        FotoEntity deleted = em.find(FotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
