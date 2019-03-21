package DAO;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Utilisateurs;


public class UtilisateurDAO {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("mysql");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction tx = manager.getTransaction();

    public Utilisateurs add(String nom, String prenom, String email) {
        tx.begin();
        Utilisateurs u = new Utilisateurs();
        try {
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            manager.persist(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return u;
    }


    public Utilisateurs update(int id, String nom, String prenom, String email) {
        tx.begin();
        Utilisateurs u = manager.find(Utilisateurs.class, id);
        try {

            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);

            manager.persist(u);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return u;
    }

    public Utilisateurs findById(int id) {
        return manager.find(Utilisateurs.class, id);
    }


    public List<Utilisateurs> findAll() {
        return manager.createQuery("Select u From Utilisateurs u", Utilisateurs.class).getResultList();
    }

    public void deleteById(int id) {
        List<Utilisateurs> resultList = manager.createQuery("Select u From Utilisateurs u where id = '" + id + "'", Utilisateurs.class).getResultList();
        for (Utilisateurs x : resultList) {
            tx.begin();
            manager.remove(x);
            tx.commit();
        }
    }


}