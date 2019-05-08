package DAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entities.Dates;
import entities.Lieus;
import entities.Sondages;
import entities.Utilisateurs;
import jpa.EntityManagerHelper;
import org.codehaus.jettison.json.JSONArray;


public class SondageDAO {
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();

    public Sondages addSondageDates(String intitule, String resume, int leResponsable, List<Dates> dates) {
        tx.begin();
        Sondages s = new Sondages();
        try {

            UtilisateurDAO udao =  new UtilisateurDAO();

            s.setIntitule(intitule);
            s.setResume(resume);
            s.setLeResponsable(udao.findById(leResponsable));
            manager.persist(s);

            PropositionDAO p = new PropositionDAO();
            for (Dates d: dates) {
                d.setMonSondage(s);
                p.addDates(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return s;
    }

    public Sondages addSondageLieu(String intitule, String resume, int leResponsable, List<Lieus> lieux) {
        tx.begin();
        Sondages s = new Sondages();
        try {

            UtilisateurDAO udao =  new UtilisateurDAO();

            s.setIntitule(intitule);
            s.setResume(resume);
            s.setLeResponsable(udao.findById(leResponsable));
            manager.persist(s);

            PropositionDAO p = new PropositionDAO();
            for (Lieus l: lieux) {
                l.setMonSondage(s);
                p.addLieux(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return s;
    }

    public Sondages update(int id, String intitule, String resume,String lienInscription,String lienRapport, Utilisateurs leResponsable) {
        tx.begin();
        Sondages s = new Sondages();
        try {
            s.setIntitule(intitule);
            s.setResume(resume);
            s.setLienInscription(lienInscription);
            s.setLienRapport(lienRapport);
            s.setLeResponsable(leResponsable);
            manager.merge(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return s;
    }

    public Sondages findById(int id) {
        return manager.find(Sondages.class, id);
    }

    public List<Sondages> findByMesSondage(int id) {
        List<Sondages> sondages = null;
        UtilisateurDAO udoa = new UtilisateurDAO();
        Utilisateurs utilisateur =  null;
        try{
            utilisateur = udoa.findById(id);
            TypedQuery<Sondages> query = manager.createQuery(
                    "SELECT s FROM Sondages s WHERE s.leResponsable = :responsable", Sondages.class);
            sondages = query.setParameter("responsable", utilisateur).getResultList();
        } catch (NoResultException nre){

        }
        System.out.println(11);

        return sondages;
    }


    public List<Sondages> findAll() {
        return manager.createQuery("Select s From Sondages s", Sondages.class).getResultList();
    }

    public void deleteById(int id) {
        Sondages sondage = manager.find(Sondages.class, id);
        tx.begin();
        manager.remove(sondage);
        tx.commit();
    }
}
