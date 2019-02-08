package jpa;

import entities.Dates;
import entities.Reunions;
import entities.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class JpaTest {
    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.initDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listUtilisateurs();

        manager.close();
        System.out.println(".. done");


    }

    public void initDataBase(){
        this.createUtilisateur();
    }

    private void createUtilisateur(){
        Utilisateurs utilisateurs1 = new Utilisateurs("DALISSON", "Emmanuel", "edalison@niji.com");
        Utilisateurs utilisateurs2 = new Utilisateurs("LANGNITO", "Constant", "clangnito@niji.com");
        Utilisateurs utilisateurs3 = new Utilisateurs("VIGNOLE", "William", "wvignole@niji.com");

        manager.persist(utilisateurs1);
        manager.persist(utilisateurs2);
        manager.persist(utilisateurs3);

        this.createReunion(utilisateurs1);


    }

    private void createReunion(Utilisateurs utilisateurs){
        Reunions reunions1 = new Reunions("Point de stage constant", "Nous allons parler " +
                "de la suite de son stage",utilisateurs );

        manager.persist(reunions1);

        createDate(reunions1);

    }


    private void createDate(Reunions reunions){
        //Date d1 = new Date("11/02/2017");
        Calendar currenttime = Calendar.getInstance();
        Dates dates1 = new Dates(new Date((currenttime.getTime()).getTime()), false,false,reunions);
        Dates dates2 = new Dates(new Date((currenttime.getTime()).getTime()), false,false,reunions);
        Dates dates3 = new Dates(new Date((currenttime.getTime()).getTime()), false,false,reunions);

        manager.persist(dates1);
        manager.persist(dates2);
        manager.persist(dates3);

    }

    private void listUtilisateurs() {
        List<Utilisateurs> resultList = manager.createQuery("Select u From Utilisateurs u", Utilisateurs.class).getResultList();
        System.out.println("num of Utilisateur:" + resultList.size());
        for (Utilisateurs next : resultList) {
            System.out.println("next Utilisateur: " + next);
        }
    }

}
