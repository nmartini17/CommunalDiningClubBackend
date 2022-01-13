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

public class Populator {
    public static void populate(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dinnerevent dinnerevent = new Dinnerevent(10,"Denmark", "Sinds mad", 10);
        Member member = new Member("Vejvej 10", 22222222, "mail@email.com", 1999, 1000);
        List<Member> memberList = new ArrayList<>();
        memberList.add(member);
        Assignment assignment = new Assignment("Stabil Fam", "contactInfo", memberList, dinnerevent);
        member.addAssignment(assignment);
        em.persist(member);
        em.persist(assignment);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        populate();
    }
}
