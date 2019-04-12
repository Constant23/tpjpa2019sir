package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entities.Utilisateurs;
import jpa.EntityManagerHelper;


public class UtilisateurDAO {
    EntityManager manager = EntityManagerHelper.getEntityManager();
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
            manager.merge(u);
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
        Utilisateurs utilisateurs = manager.find(Utilisateurs.class, id);
        tx.begin();
        manager.remove(utilisateurs);
        tx.commit();
    }

    public Utilisateurs getLogin(String email) {
        Utilisateurs utilisateurs = null;
        try{
            TypedQuery<Utilisateurs> query = manager.createQuery(
                    "SELECT u FROM Utilisateurs u WHERE u.email = :email", Utilisateurs.class);
            utilisateurs = query.setParameter("email", email).getSingleResult();
            } catch (NoResultException nre){

        }

        return utilisateurs;
    }
}
