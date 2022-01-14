package facades;

import dtos.MemberDTO;
import entities.Assignment;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getMemberFacade(emf);
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
            List<Member> memberList = new ArrayList<>();
            Member member = new Member("Vejvej 10", 22222222, "mail@email.com", 1999, 1000);
            Member member1 = new Member("Vejvej 11", 33333333, "mail1@email.com", 1998, 1000);
            Member member2 = new Member("Vejvej 12", 44444444, "mail2@email.com", 1997, 1000);
            memberList.add(member);

            Dinnerevent di = new Dinnerevent(2230, "Denmark", "Flæsk", 125);

            Assignment as = new Assignment("famName", "contact@me.dk", memberList, di);

            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(member);
            em.persist(member1);
            em.persist(member2);
            em.persist(as);
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
    public void testGetAllMembers() throws Exception {
        assertEquals(3, facade.getAllMembers().size(), "Expects 3 Members");
    }

    @Test
    public void testCreateMember() throws InvalidInputException {
        MemberDTO mDTO = new MemberDTO("Testvej 10", 11223344, "test@email.com", 1995, 1212);
        facade.createMember(mDTO);
        assertEquals("test@email.com", mDTO.getEmail());
    }

    @Test
    public void testGetAccountStatus() throws Exception {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        int id = query.getResultList().get(0).getId();
        assertEquals(1000,facade.getAccountStatus(id).getAccount(), "Expects amount in account");
    }

    //@Test
    public void testAddMemberToAssignment() throws Exception {
        MemberDTO mDTO = new MemberDTO("Vejvej 12", 44444444, "mail2@email.com", 1997, 1235);
        facade.addMemberToAssignment(mDTO);
    }

}
