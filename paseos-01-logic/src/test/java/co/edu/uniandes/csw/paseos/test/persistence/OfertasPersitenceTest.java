package co.edu.uniandes.csw.paseos.test.persistence;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import co.edu.uniandes.csw.paseos.persistence.OfertaPersistence;
import co.edu.uniandes.csw.paseos.persistence.PaseoPersistence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class OfertasPersitenceTest {
    
    public static final String DEPLOY = "PruebaOfertaPersitence";
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(OfertaEntity.class.getPackage())
                .addClass(OfertaPersistence.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private OfertaPersistence persitence;
    
    @PersistenceContext(unitName = "paseosPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<OfertaEntity> data = new ArrayList<OfertaEntity>();
    
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

    
    private void clearData() {
        em.createQuery("delete from OfertaEntity").executeUpdate();
    }
    
   
    private void insertData(int n) {
        for (int i = 0; i < n; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            OfertaEntity entity = factory.manufacturePojo(OfertaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test
    public void createOfertaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity oferta = factory.manufacturePojo(OfertaEntity.class);
        OfertaEntity result = persitence.create(oferta);
        assertNotNull(result);
        
        OfertaEntity entity = em.find(OfertaEntity.class, result.getId());
        
        assertEquals(result.getId(),entity.getId());
        
        assertEquals(oferta.getFecha(),entity.getFecha());
        assertEquals(oferta.getGuia(),entity.getGuia());
        assertEquals(oferta.getInscritos(),entity.getInscritos());
        assertEquals(oferta.getPaseo(),entity.getPaseo());
        assertEquals(oferta.getVisitas(),entity.getVisitas());        
    }
   
    @Test
    public void getOfertasTest(){
        List<OfertaEntity> list = persitence.findAll();
        assertEquals(list.size(), data.size());
        
        for (OfertaEntity oferta : list){
            boolean found=false;
            for (OfertaEntity entity : data) {
                if (oferta.getId().equals(entity.getId())) {
                    found = true;           
                    assertEquals(oferta.getFecha(),entity.getFecha());
                    assertEquals(oferta.getGuia(),entity.getGuia());
                    assertEquals(oferta.getInscritos(),entity.getInscritos());
                    assertEquals(oferta.getPaseo(),entity.getPaseo());
                    assertEquals(oferta.getVisitas(),entity.getVisitas());
                }
            }
            assertTrue(found);
            
        }
    }
    

    @Test
    public void getOfertaTest() {
        OfertaEntity oferta = data.get(0);
        OfertaEntity entity = persitence.find(oferta.getId());
        assertNotNull(entity);
        
        assertEquals(oferta.getFecha(),entity.getFecha());
        assertEquals(oferta.getGuia(),entity.getGuia());
        assertEquals(oferta.getInscritos(),entity.getInscritos());
        assertEquals(oferta.getPaseo(),entity.getPaseo());
        assertEquals(oferta.getVisitas(),entity.getVisitas());
    }
    
    
    @Test
    public void deleteOfertaTest() {
        OfertaEntity entity = data.get(0);
        persitence.delete(entity.getId());
        OfertaEntity deleted = em.find(OfertaEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    public void updateOfertaTest() {
        OfertaEntity ent = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OfertaEntity entity = factory.manufacturePojo(OfertaEntity.class);
        entity.setId(ent.getId());

        persitence.update(entity);

        OfertaEntity oferta = em.find(OfertaEntity.class, ent.getId());

        assertNotNull(oferta);
        
        assertEquals(oferta.getFecha(),entity.getFecha());
        assertEquals(oferta.getGuia(),entity.getGuia());
        assertEquals(oferta.getInscritos(),entity.getInscritos());
        assertEquals(oferta.getPaseo(),entity.getPaseo());
        assertEquals(oferta.getVisitas(),entity.getVisitas());
    }
       
    }
