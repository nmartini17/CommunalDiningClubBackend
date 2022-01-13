/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BoatFacade FACADE =  BoatFacade.getBoatFacade(emf);

        System.out.println(FACADE.getHarbourByName("Thors havn"));
        /*Owner owner = new Owner("Jens", "Vejvej 10", 12345678);
        Owner owner1 = new Owner("Mikkel Hansen", "Gayvej 25", 11223344);
        Harbour harbour = new Harbour("Havnhavn", "Havnhavnevejgade 25", 69);
        Boat boat = new Boat("Ferrari", "Ferrari boat", "FerrariBoat", "https://www.boat24.com/fotos/xlarge/341796-abcbdb664172330ce1008fab221c58ad-x-1463319-5f43b6c9052ae4b5c128d4b2dedb7c31.jpg", harbour);
        owner1.addBoat(boat);
        em.persist(boat);
        em.getTransaction().commit();
        em.close();*/
    }

    public static void main(String[] args) {
        populate();
    }
}
