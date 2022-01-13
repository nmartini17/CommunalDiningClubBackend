package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import dtos.UserDTO;
import entities.*;
import security.errorhandling.AuthenticationException;

import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class BoatFacade {
    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BoatFacade() {
    }

    public static BoatFacade getBoatFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<BoatDTO> showBoatsFromHarbour(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT h FROM Boat h JOIN h.harbour n WHERE n.name = :name", Boat.class);
            query.setParameter("name", name);
            List<Boat> be = query.getResultList();
            return BoatDTO.getBoatDTO(be);
        } finally {
            em.close();
        }
    }

    public BoatDTO createBoat(BoatDTO boatdto) {
        EntityManager em = emf.createEntityManager();
        List<Owner> ownerList = new ArrayList<>();
        List<OwnerDTO> ownerDTOList = boatdto.getOwnerList();
        for (OwnerDTO ownerDTO : ownerDTOList) {
            Owner owner1 = new Owner(ownerDTO.getName(), ownerDTO.getAddress(), ownerDTO.getPhone());
            ownerList.add(owner1);
        }
        Harbour harbour = getHarbourByName(boatdto.getHarbourDTO().getName());
        Boat boat = new Boat(boatdto.getBrand(), boatdto.getMake(), boatdto.getName(), boatdto.getImage(), harbour, ownerList);
        try {
            em.getTransaction().begin();
            em.merge(boat);
            em.getTransaction().commit();
            return new BoatDTO(boat);
        } finally {
            em.close();
        }
    }

    public Harbour getHarbourByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT h FROM Harbour h where h.name = :name", Harbour.class);
            query.setParameter("name", name);
            System.out.println(query.getSingleResult());
            Harbour ha = (Harbour) query.getSingleResult();
            return ha;
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
    }

    public String deleteBoat(int id) {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, id);
        try {
            if (boat == null) {
                throw new EntityNotFoundException("Could not find any boat with id: " + id);
            }
            em.getTransaction().begin();
            em.remove(boat);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            throw new WebApplicationException(enfe.getMessage(), 404);
        } finally {
            em.close();
        }
        return "Boat with id: " + id + " was successfully deleted";
    }

    public BoatDTO editBoat(BoatDTO bdto) {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, bdto.getId());
        try {
            boat.setBrand(bdto.getBrand());
            boat.setMake(bdto.getMake());
            boat.setName(bdto.getName());
            boat.setImage(bdto.getImage());

            Harbour ha = getHarbourByName(bdto.getHarbourDTO().getName());

            boat.setHarbour(ha);

            em.getTransaction().begin();
            em.merge(boat);
            em.getTransaction().commit();
            return new BoatDTO(boat);
        } finally {
            em.close();
        }
    }
}
