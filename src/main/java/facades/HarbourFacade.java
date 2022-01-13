package facades;

import entities.Harbour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HarbourFacade {
    private static HarbourFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HarbourFacade() {}

    public static HarbourFacade getHarbourFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Harbour getHarbourByID(int id){
        EntityManager em = getEntityManager();
        return em.find(Harbour.class, id);
    }
}
