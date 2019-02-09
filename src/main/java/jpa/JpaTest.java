package jpa;

import entities.Dates;
import entities.Participants;
import entities.Reunions;
import entities.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {
    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    private List<Utilisateurs> allUtilisateurs = new ArrayList<Utilisateurs>();
    private List<Dates> allDates = new ArrayList<Dates>();
    private Reunions _reunions ;


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

    public void initDataBase() throws ParseException {
        this.createUtilisateur();
    }

    private void createUtilisateur() throws ParseException {
        Utilisateurs utilisateurs1 = new Utilisateurs("DALISSON", "Emmanuel", "edalison@niji.com");
        Utilisateurs utilisateurs2 = new Utilisateurs("LANGNITO", "Constant", "clangnito@niji.com");
        Utilisateurs utilisateurs3 = new Utilisateurs("VIGNOLE", "William", "wvignole@niji.com");
        Utilisateurs utilisateurs4 = new Utilisateurs("LEBIANIC", "Vincent", "vlebianic@niji.com");

        allUtilisateurs.add(utilisateurs1);
        allUtilisateurs.add(utilisateurs2);
        allUtilisateurs.add(utilisateurs3);
        allUtilisateurs.add(utilisateurs4);

        manager.persist(utilisateurs1);
        manager.persist(utilisateurs2);
        manager.persist(utilisateurs3);
        manager.persist(utilisateurs4);

        this.createReunion(utilisateurs1);
    }

    private void createReunion(Utilisateurs utilisateurs) throws ParseException {
        Reunions reunions1 = new Reunions("Point de stage constant", "Nous allons parler " +
                "de la suite de son stage",utilisateurs );

        _reunions = reunions1;

        manager.persist(reunions1);

        createDate(reunions1);
    }


    private void createDate(Reunions reunions){
        //Date d1 = new Date("11/02/2017");

        try {
            //first convert string to java.util.Date object using SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            Dates dates1 = new Dates(new Date(sdf.parse("2019-03-25 10:00:00").getTime()), false,false,reunions);
            Dates dates2 = new Dates(new Date(sdf.parse("2019-03-26 10:00:00").getTime()), false,false,reunions);
            Dates dates3 = new Dates(new Date(sdf.parse("2019-03-27 12:00:00").getTime()), false,false,reunions);

            /*allDates.add(dates1);
            allDates.add(dates2);
            allDates.add(dates3);*/


            List<Utilisateurs> utilisateursDate1 = new ArrayList<Utilisateurs>();
            utilisateursDate1.add(allUtilisateurs.get(1));
            utilisateursDate1.add(allUtilisateurs.get(2));
            utilisateursDate1.add(allUtilisateurs.get(3));
            dates1.setMesUtilisateurs(utilisateursDate1);
            dates2.setMesUtilisateurs(utilisateursDate1);

            List<Utilisateurs> utilisateursDate3 = new ArrayList<Utilisateurs>();
            utilisateursDate3.add(allUtilisateurs.get(2));
            dates3.setMesUtilisateurs(utilisateursDate3);


            manager.persist(dates1);
            manager.persist(dates2);
            manager.persist(dates3);

            validerDate(dates1,utilisateursDate1);

        } catch (ParseException e) {
            //Handle exception here, most of the time you will just log it.
            e.printStackTrace();
        }

    }

    private void validerDate(Dates dates,List<Utilisateurs> userInforme){
        dates.setaUnDejeuner(true);
        dates.setEstValider(true);

        informerParticipant(userInforme,_reunions);
    }

    private void informerParticipant(List<Utilisateurs> userInforme,Reunions reunion){
        for (Utilisateurs utilisateur:userInforme) {
            Participants participants = new Participants(utilisateur,reunion);
            manager.persist(participants);
        }

    }


    private void listUtilisateurs() {
        List<Utilisateurs> resultList = manager.createQuery("Select u From Utilisateurs u", Utilisateurs.class).getResultList();
        System.out.println("num of Utilisateur:" + resultList.size());
        for (Utilisateurs next : resultList) {
            System.out.println("next Utilisateur: " + next);
        }
    }

}
