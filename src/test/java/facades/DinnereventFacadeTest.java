package facades;

import dtos.DinnereventDTO;
import dtos.MemberDTO;
import entities.Dinnerevent;
import entities.Member;
import errorhandling.InvalidInputException;
import utils.EMF_Creator;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class DinnereventFacadeTest {

    private static EntityManagerFactory emf;
    private static DinnereventFacade facade;

    public DinnereventFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = DinnereventFacade.getDinnereventFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            Dinnerevent di = new Dinnerevent(2230, "Denmark", "Pasta", 125);
            Dinnerevent di1 = new Dinnerevent(2130, "Sweden", "Hestekødboller", 75);
            Dinnerevent di2 = new Dinnerevent(0330, "Norge", "Fisk", 600);

            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.createNamedQuery("Dinnerevent.deleteAllRows").executeUpdate();
            em.persist(di);
            em.persist(di1);
            em.persist(di2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void testGetAllEvents() throws Exception {
    assertEquals(3, facade.getAllEvents().size(), "Expects 3 Dinner Events");
    }

    @Test
    public void testCreateMember() throws InvalidInputException {
        DinnereventDTO dDTO = new DinnereventDTO(1930, "Norge", "Meget Fisk", 700);
        facade.createEvent(dDTO );
        assertEquals("Norge", dDTO.getLocation());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Dinnerevent> query = em.createQuery("SELECT d FROM Dinnerevent d", Dinnerevent.class);
        int id = query.getResultList().get(0).getId();
        facade.deleteEvent(id);
        assertNotEquals(3, 2, "3 events in total, expects 2 after having deleted 1 event");
    }
}
