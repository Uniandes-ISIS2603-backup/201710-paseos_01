package co.edu.uniandes.csw.paseos.test.logic;


import co.edu.uniandes.csw.paseos.ejbs.VisitaLogic;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import co.edu.uniandes.csw.paseos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.paseos.persistence.VisitaPersistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class VisitaLogicTest {

    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(VisitaEntity.class.getPackage())
                .addPackage(VisitaLogic.class.getPackage())
                .addPackage(VisitaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private VisitaLogic visitaLogic;
    
    @Inject
    private VisitaPersistence visitaPersitence;
    
    @Inject
    private OfertaPersistence ofertaPersitence;
    
    @Inject
    private UsuarioPersistence usuarioPersitence;
    
    @Inject
    private PaseoPersistence paseoPersitence;

    /**
     * @generated
     */
    @PersistenceContext(unitName = "paseosPU")
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
        em.createQuery("delete from OfertaEntity").executeUpdate();     
        em.createQuery("delete from PaseoEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
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
            OfertaEntity oferta = factory.manufacturePojo(OfertaEntity.class);
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            PaseoEntity paseo = factory.manufacturePojo(PaseoEntity.class);
            oferta.setPaseo(paseo);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE,5);
            Date fecha = c.getTime();
            oferta.setFecha(fecha);
            em.persist(paseo);
            em.persist(oferta);
            em.persist(usuario);
            entity.setOferta(oferta);
            entity.setUsuario(usuario);
            em.persist(entity);          
            
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            VisitaEntity entity = factory.manufacturePojo(VisitaEntity.class);
            OfertaEntity oferta = factory.manufacturePojo(OfertaEntity.class);
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            PaseoEntity paseo = factory.manufacturePojo(PaseoEntity.class);
            oferta.setPaseo(paseo);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE,-5);
            Date fecha = c.getTime();
            oferta.setFecha(fecha);
            em.persist(paseo);
            em.persist(oferta);
            em.persist(usuario);
            entity.setOferta(oferta);
            entity.setUsuario(usuario);
            em.persist(entity);          
            
            data.add(entity);
        }
    }
    
    /*@Test
    public void setOfertaYUsuarioVisitaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VisitaEntity entity = data.get(0);
        OfertaEntity oferta = factory.manufacturePojo(OfertaEntity.class);
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        ofertaPersitence.create(oferta);
        usuarioPersitence.create(usuario);
        VisitaEntity result = null;
        
        try {
            entity.setOferta(oferta);
            entity.setUsuario(usuario);
            visitaLogic.setUsuarioYOferta(entity);
            result = visitaPersitence.find(entity.getId());
        } catch (BusinessLogicException ex) {
            Assert.fail(ex.getMessage());
            Logger.getLogger(VisitaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertNotNull(result);
        
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertArrayEquals(result.getFotos().toArray(), entity.getFotos().toArray());
        Assert.assertEquals(result.getOferta().getId(), entity.getOferta().getId());
        Assert.assertEquals(result.getUsuario().getId(), entity.getUsuario().getId());
        Assert.assertEquals(result.getComentario(), entity.getComentario());
        Assert.assertEquals(result.getCalificacion(), entity.getCalificacion());
   }*/

    /**
     * Prueba para crear un Book.
     *
     * @generated
     */
    @Test
    public void createVisitaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VisitaEntity entity = factory.manufacturePojo(VisitaEntity.class);
        VisitaEntity result = null;
        
        try {
            result = visitaLogic.createVisita(entity);
            data.add(result);
            
        } catch (BusinessLogicException ex) {
            Assert.fail(ex.getMessage());
            Logger.getLogger(VisitaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertNotNull(result);
        
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertArrayEquals(result.getFotos().toArray(), entity.getFotos().toArray());
        Assert.assertNull(result.getOferta());
        Assert.assertNull(result.getUsuario());
        Assert.assertEquals(result.getComentario(), entity.getComentario());
        Assert.assertEquals(result.getCalificacion(), entity.getCalificacion());
   }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getVisitasTest() {
        List<VisitaEntity> list = visitaLogic.getVisita();
        Assert.assertEquals(data.size(), list.size());
        for (VisitaEntity entity : list) {
            boolean found = false;
            for (VisitaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getVisitasPorUsuarioTest() {
        UsuarioEntity usuario = data.get(0).getUsuario();
        List<VisitaEntity> list = visitaLogic.getVisitaPorUsuario(usuario.getId());
        for (VisitaEntity entity : list) {
            boolean found = false;
            for (VisitaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getVisitasPorPaseoTest() {
        PaseoEntity paseo = data.get(0).getOferta().getPaseo();
        List<VisitaEntity> list = visitaLogic.getVisitaPorPaseo(paseo.getId());
        for (VisitaEntity entity : list) {
            boolean found = false;
            for (VisitaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
    public void getVisitaTest() {
        VisitaEntity entity = data.get(0);
        VisitaEntity resultEntity = visitaLogic.getVisita(entity.getId());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertArrayEquals(resultEntity.getFotos().toArray(), entity.getFotos().toArray());
        Assert.assertEquals(resultEntity.getOferta().getId(), entity.getOferta().getId());
        Assert.assertEquals(resultEntity.getUsuario().getId(), entity.getUsuario().getId());
        Assert.assertEquals(resultEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(resultEntity.getCalificacion(), entity.getCalificacion());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateVisitaTest() {
        VisitaEntity entity = data.get(3);
        PodamFactory factory = new PodamFactoryImpl();
        VisitaEntity pojoEntity = factory.manufacturePojo(VisitaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setOferta(entity.getOferta());
        pojoEntity.setUsuario(entity.getUsuario());
        
        VisitaEntity resp = null;

        try {
            resp = visitaLogic.updateVisita(pojoEntity);
            
        } catch (BusinessLogicException ex) {
            Assert.fail(ex.getMessage());
            Logger.getLogger(VisitaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getOferta().getId(), resp.getOferta().getId());
        Assert.assertEquals(pojoEntity.getUsuario().getId(), resp.getUsuario().getId());
        Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(pojoEntity.getCalificacion(), resp.getCalificacion());
    }
    
    /**
     * Prueba para eliminar un Book.
     *
     * @generated
     */
    /*
    @Test
    public void deleteVisitaTest() {
        
        VisitaEntity entity = data.get(0);
        ArrayList<VisitaEntity> list= new ArrayList<VisitaEntity>(); 
        list.add(entity);
        entity.getOferta().setVisitas(list);
        ofertaPersitence.update(entity.getOferta());
        entity.getUsuario().setVisitas(list);
        usuarioPersitence.update(entity.getUsuario());
        
        try {
            visitaLogic.deleteVisita(entity.getId());
        } catch (BusinessLogicException ex) {
            Assert.fail(ex.getMessage());
            Logger.getLogger(VisitaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        VisitaEntity deleted = em.find(VisitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }*/

}
