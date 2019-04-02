package DAO;


import entities.Dates;
import entities.Propositions;
import entities.Sondages;
import entities.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class SondageDAO {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("mysql");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction tx = manager.getTransaction();

     public Sondages add(String intitule, String resume, Utilisateurs utilisateurs) {
        tx.begin();
        Sondages s = new Sondages();
        try {
            s.setIntitule(intitule);
            s.setResume(resume);
            s.setLeResponsable(utilisateurs);
            manager.persist(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return s;
    }



    public Sondages update(int id, String intitule, String resume,String lienInscription, String lienRapport, Utilisateurs utilisateurs) {
        tx.begin();
        Sondages s = manager.find(Sondages.class, id);
        try {

            s.setIntitule(intitule);
            s.setResume(resume);
            s.setResume(lienInscription);
            s.setResume(lienRapport);
            s.setLeResponsable(utilisateurs);
            manager.persist(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return s;
    }

    public Sondages findById(int id) {
        return manager.find(Sondages.class, id);
    }


    public List<Sondages> findAll() {
        return manager.createQuery("Select s From Sondages s", Sondages.class).getResultList();
    }

    public void deleteById(int id) {
        List<Sondages> resultList = manager.createQuery("Select s From Sondages s where id = '" + id + "'", Sondages.class).getResultList();
        for (Sondages x : resultList) {
            tx.begin();
            manager.remove(x);
            tx.commit();
        }
    }

    public void addDates(Sondages sondage, List<Dates> dates) {
        tx.begin();
        for(Dates d : dates) {
            sondage.getMesPropositions().add(d);
        }
        manager.merge(sondage);
        tx.commit();
    }


}