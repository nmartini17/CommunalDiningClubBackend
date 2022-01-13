package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import entities.Boat;
import entities.Harbour;
import entities.RenameMe;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoatFacadeTest {
    private static EntityManagerFactory emf;
    private static BoatFacade facade;

    public BoatFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = BoatFacade.getBoatFacade(emf);
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
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            //Harbour harbour = new Harbour("Sydhavn", "Sydhavnsvejsgade 1", 10);
            //em.persist(harbour);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        //Remove any data after each test was run
    }

    // TODO: Delete or change this method
    //@Test
    /*public void testCreateBoat() throws Exception {
        EntityManager em = emf.createEntityManager();
        Harbour harbour = new Harbour("Sydhavn", "Sydhavnsvejsgade 1", 10);
        Boat boat = new Boat("Båd", "Thomas", "Plain Jane", "https://www.youtube.com/watch?v=meXPbvp3ldg", harbour);
        BoatDTO boatDTO = new BoatDTO(boat);
        assertEquals("Plain Jane", facade.createBoat(boatDTO).getName());
    }*/

}
