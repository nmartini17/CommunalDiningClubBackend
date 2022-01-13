package facades;

import dtos.DinnereventDTO;
import dtos.MemberDTO;
import dtos.RenameMeDTO;
import entities.Assignment;
import entities.Dinnerevent;
import entities.Member;
import entities.RenameMe;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemberFacade {
    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {}

    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<MemberDTO> getAllMembers(){
        EntityManager em = getEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> memberList = query.getResultList();
        return MemberDTO.getMemberDTO(memberList);
    }

    public MemberDTO addMemberToAssignment(MemberDTO memberDTO){
        EntityManager em = getEntityManager();
        Member member = new Member(memberDTO.getAddress(), memberDTO.getPhone(), memberDTO.getEmail(), memberDTO.getBdayyear(), memberDTO.getAccount());
        em.getTransaction().begin();
        em.persist(member);
        for (Assignment assignment: member.getAssignmentList()){
            member.addAssignment(assignment);
            em.merge(member);
            em.getTransaction().commit();
        }
        em.close();
        return new MemberDTO(member);
    }

    public MemberDTO getAccountStatus(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        Member member = em.find(Member.class, id);
        if (member == null)
            throw new NotFoundException("No member with ID: " + id + " Was found!");
        return new MemberDTO(member);
    }

    public MemberDTO createMember(MemberDTO memberDTO) throws InvalidInputException {
        EntityManager em = emf.createEntityManager();
        Member member = new Member(memberDTO.getAddress(), memberDTO.getPhone(), memberDTO.getEmail(), memberDTO.getBdayyear(), memberDTO.getAccount());
        try{
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
            //} catch (InvalidInputException e){
            //throw new WebApplicationException(e.getMessage(), 400);
        } finally {
            em.close();
        }
        return new MemberDTO(member);
    }
}
