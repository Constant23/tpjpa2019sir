package DAO;


import entities.Participants;
import entities.Sondages;
import entities.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class ParticipantDAO {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("mysql");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction tx = manager.getTransaction();

    public Participants add(Utilisateurs utilisateur, Sondages sondage) {
        tx.begin();
        Participants p = new Participants(utilisateur,sondage);
        try {
            manager.persist(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return p;
    }

    private String lienUnique;

    private String preference;

    private String allergies;

    private String clearCode;

    private boolean present;

    private Utilisateurs utilisateur;

    private Sondages sondage;

    public Participants update(int id, Utilisateurs utilisateur, Sondages sondage,
                               String lienUnique, String preference, String allergies, String clearCode, boolean present) {
        tx.begin();
        Participants p = manager.find(Participants.class, id);
        try {

            p.setUtilisateur(utilisateur);
            p.setSondage(sondage);
            p.setLienUnique(lienUnique);
            p.setPreference(preference);
            p.setAllergies(allergies);
            p.setClearCode(clearCode);
            p.setPresent(present);

            manager.persist(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return p;
    }

    public Participants findById(int id) {
        return manager.find(Participants.class, id);
    }


    public List<Participants> findAll() {
        return manager.createQuery("Select p From Participants p", Participants.class).getResultList();
    }

    public void deleteById(int id) {
        List<Participants> resultList = manager.createQuery("Select p From Participants p where id = '" + id + "'", Participants.class).getResultList();
        for (Participants x : resultList) {
            tx.begin();
            manager.remove(x);
            tx.commit();
        }
    }


}