package co.edu.uniandes.csw.paseos.test.logic;

import co.edu.uniandes.csw.paseos.ejbs.OfertaLogic;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;

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


@RunWith(Arquillian.class)
public class OfertaLogicTest{
     public static final String DEPLOY = "PruebaOfertaLogic";

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(OfertaEntity.class.getPackage())
                .addPackage(OfertaPersistence.class.getPackage())
                .addPackage(OfertaLogic.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }


  
    @Inject
    private OfertaLogic ofertaLogic;

    @PersistenceContext(unitName = "paseosPU")
    private EntityManager em;

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
     * Prueba para crear una oferta
     */
    @Test
    public void createOfertaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity entity = factory.manufacturePojo(OfertaEntity.class);
        OfertaEntity result =null;
        entity.setFecha(agregaDias(hoy,5));
        result = ofertaLogic.createOferta(entity);
        
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getFecha().getDay(), entity.getFecha().getDay());
        Assert.assertEquals(result.getFecha().getMonth(), entity.getFecha().getMonth());
        Assert.assertEquals(result.getFecha().getYear(), entity.getFecha().getYear());
        Assert.assertEquals(result.getGuia(), entity.getGuia());
        Assert.assertEquals(result.getPaseo(), entity.getPaseo());
    }
    /**
     * Prueba para consultar una Oferta
     */
    @Test
    public void getOfertaTest() {
        OfertaEntity entity = data.get(0);
        OfertaEntity resultEntity= null ;
         try {
             resultEntity = ofertaLogic.getOferta(entity.getId());
         } catch (BusinessLogicException ex) {
             Assert.fail("No deberia generar excepción"); 
         }
        Assert.assertEquals(resultEntity.getFecha().getDay(), entity.getFecha().getDay());
        Assert.assertEquals(resultEntity.getFecha().getMonth(), entity.getFecha().getMonth());
        Assert.assertEquals(resultEntity.getFecha().getYear(), entity.getFecha().getYear());
        Assert.assertEquals(resultEntity.getGuia(), entity.getGuia());
        Assert.assertEquals(resultEntity.getPaseo(), entity.getPaseo());
    }
    /**
     * Prueba encontrar un usuario que no existe
     */
    @Test
    public void getOfertaTest1() {
        OfertaEntity entity = data.get(0);
        OfertaEntity resultEntity= null ;
         try {
             resultEntity = ofertaLogic.getOferta(100L);
         } catch (BusinessLogicException ex) {
             Assert.assertEquals(1, 1);  
         }
      
    }
    
    /**
     * Prueba para consultar la lista de ofertas
     */
    @Test
    public void getOfertasTest() {
        List<OfertaEntity> list = ofertaLogic.getOfertas();
        Assert.assertEquals(data.size(), list.size());
        for (OfertaEntity entity : list) {
            boolean found = false;
            for (OfertaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para eliminar una oferta
     */
    @Test
    public void deleteOfertaTest() {
        OfertaEntity entity = data.get(0);
        entity.setFecha(agregaDias(hoy,5));
        em.persist(entity);
        try {
             ofertaLogic.deleteOferta(entity.getId());
         } catch (BusinessLogicException ex) {
            Assert.fail("No deberia generar excepción"); 
         }
        OfertaEntity deleted = em.find(OfertaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba eliminar un usuario que no existe
     */
     @Test
    public void deleteOfertaTest1() {
        OfertaEntity entity = data.get(0);
         try {
             ofertaLogic.deleteOferta(100L);
         } catch (BusinessLogicException ex) {
            Assert.assertEquals(1, 1); 
         }
        
    }

    /**
     * Prueba para actualizar una oferta
     */
    @Test
    public void updateOfertaTest() throws BusinessLogicException {
        OfertaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity pojoEntity = factory.manufacturePojo(OfertaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setFecha(agregaDias(hoy,5));
        ofertaLogic.updateOferta(pojoEntity);

        OfertaEntity resp = em.find(OfertaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getFecha().getDay(), resp.getFecha().getDay());
        Assert.assertEquals(pojoEntity.getFecha().getMonth(), resp.getFecha().getMonth());
        Assert.assertEquals(pojoEntity.getFecha().getYear(), resp.getFecha().getYear());
        Assert.assertEquals(pojoEntity.getGuia(), resp.getGuia());
        Assert.assertEquals(pojoEntity.getPaseo(), resp.getPaseo());
    }
    
    private Date agregaDias(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
  }
    Date hoy = new Date();
}
