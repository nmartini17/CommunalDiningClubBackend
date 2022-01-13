package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import entities.Boat;
import entities.Owner;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OwnerFacade {
    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OwnerFacade() {}

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<OwnerDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owner = query.getResultList();
        return OwnerDTO.getOwnerDTO(owner);
    }

    public OwnerDTO addOwnerToBoat(OwnerDTO ownerDTO){
        EntityManager em = emf.createEntityManager();
        Owner owner = new Owner(ownerDTO.getName(), ownerDTO.getAddress(), ownerDTO.getPhone());
        em.getTransaction().begin();
        em.persist(owner);
        for (Boat boat1: owner.getBoatList()){
            owner.addBoat(boat1);
            em.merge(owner);
            em.getTransaction().commit();
        }
        em.close();
        return new OwnerDTO(owner);
    }

    public List<OwnerDTO> showAllBoatOwners(String boat){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT o FROM Boat n JOIN n.ownerList o WHERE n.name = :name", Owner.class);
            query.setParameter("name", boat);
            List<Owner> oe = query.getResultList();
            return OwnerDTO.getOwnerDTO(oe);
        } finally {
            em.close();
        }
    }
}
