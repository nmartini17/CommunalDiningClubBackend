package facades;

import dtos.AssignmentDTO;
import dtos.DinnereventDTO;
import dtos.MemberDTO;
import entities.Assignment;
import entities.Dinnerevent;
import entities.Member;
import errorhandling.InvalidInputException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class DinnereventFacade {
    private static DinnereventFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DinnereventFacade() {}

    public static DinnereventFacade getDinnereventFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnereventFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<DinnereventDTO> getAllEvents(){
        EntityManager em = getEntityManager();
        TypedQuery<Dinnerevent> query = em.createQuery("SELECT d FROM Dinnerevent d", Dinnerevent.class);
        List<Dinnerevent> DinnereventList = query.getResultList();
        return DinnereventDTO.getDinnerDTO(DinnereventList);
    }

    /*public AssignmentDTO addFamilyToEvent(AssignmentDTO adto){
        EntityManager em = getEntityManager();
        Member member = new Member(adto.getMemberDTO().getAddress(), adto.getMemberDTO().getPhone(), adto.getMemberDTO().getEmail(), adto.getMemberDTO().getBdayyear(), adto.getMemberDTO().getAccount());
        Assignment assignment = new Assignment(adto.getFamName(), adto.getContactInfo(), adto.getMemberDTOList(), adto.getDinner);
        em.getTransaction().begin();
        em.persist(member);
        for (Assignment assignment: member.getAssignmentList()){
            member.addAssignment(assignment);
            em.merge(member);
            em.getTransaction().commit();
        }
        em.close();
        return new MemberDTO(member);
    }*/

    public DinnereventDTO createEvent(DinnereventDTO dinnereventDTO) throws InvalidInputException {
        EntityManager em = emf.createEntityManager();
        Dinnerevent di = new Dinnerevent(dinnereventDTO.getTime(), dinnereventDTO.getLocation(), dinnereventDTO.getDish(), dinnereventDTO.getPrice());
        try{
            em.getTransaction().begin();
            em.persist(di);
            em.getTransaction().commit();
        //} catch (InvalidInputException e){
            //throw new WebApplicationException(e.getMessage(), 400);
        } finally {
            em.close();
        }
        return new DinnereventDTO(di);
    }

    public String deleteEvent(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Dinnerevent di = em.find(Dinnerevent.class, id);
        try{
            if (di == null){
                throw new EntityNotFoundException("No event with id: " + id);
            }
            em.getTransaction().begin();
            em.remove(di);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe){
            throw new WebApplicationException(enfe.getMessage(), 404);
        } finally {
            em.close();
        }
        return "Event with id: " + id + " was successfully deleted";
    }

    public DinnereventDTO editEvent(DinnereventDTO dDTO) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Dinnerevent di = em.find(Dinnerevent.class, dDTO.getId());
        try{
            if (di == null){
                throw new EntityNotFoundException("Could not find anyone with that id");
            }
            em.getTransaction().begin();
            di.setTime(dDTO.getTime());
            di.setLocation(dDTO.getLocation());
            di.setDish(dDTO.getDish());
            di.setPrice(dDTO.getPrice());
            em.getTransaction().commit();
            return new DinnereventDTO(di);
        } finally {
            em.close();
        }
    }
}
