package jpa;


import entities.Utilisateurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {
    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    private List<Utilisateurs> allUtilisateurs = new ArrayList<Utilisateurs>();
    //private List<Dates> allDates = new ArrayList<Dates>();
    //private Sondages _sondages;


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

        //test.listUtilisateurs();

        manager.close();
        System.out.println("fin des operations");


    }

    public void initDataBase() throws ParseException {
        this.createUtilisateur();
    }

    private void createUtilisateur() throws ParseException {
        Utilisateurs utilisateurs1 = new Utilisateurs("DALISSON", "Emmanuel", "edalison@niji.com");
        Utilisateurs utilisateurs2 = new Utilisateurs("LANGNITO", "Constant", "clangnito@niji.com");
        Utilisateurs utilisateurs3 = new Utilisateurs("VIGNOLE", "William", "wvignole@niji.com");
        Utilisateurs utilisateurs4 = new Utilisateurs("LEBIANIC", "Vincent", "vlebianic@niji.com");
        Utilisateurs utilisateurs5 = new Utilisateurs("VILON", "Sebastien", "svilon@niji.com");

        allUtilisateurs.add(utilisateurs1);
        allUtilisateurs.add(utilisateurs2);
        allUtilisateurs.add(utilisateurs3);
        allUtilisateurs.add(utilisateurs4);
        allUtilisateurs.add(utilisateurs5);

        manager.persist(utilisateurs1);
        manager.persist(utilisateurs2);
        manager.persist(utilisateurs3);
        manager.persist(utilisateurs4);
        manager.persist(utilisateurs5);

       // manager.remove(utilisateurs3);

        //this.createReunion(utilisateurs1);
    }

    /*private void createReunion(UtilisateursService utilisateurs) throws ParseException {
        // _sondages = new Sondages("Point de stage constant",
        //        "Nous allons parler " + "de la suite de son stage",utilisateurs );


        manager.persist(_sondages);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            Dates dates1 = new Dates(new Date(sdf.parse("2019-03-25 10:00:00").getTime()), false,false, _sondages);
            Dates dates2 = new Dates(new Date(sdf.parse("2019-03-26 10:00:00").getTime()), false,false, _sondages);
            Dates dates3 = new Dates(new Date(sdf.parse("2019-03-27 12:00:00").getTime()), false,true, _sondages);

            allDates.add(dates1);
            allDates.add(dates2);
            allDates.add(dates3);

        } catch (ParseException e) {
            //Handle exception here, most of the time you will just log it.
            e.printStackTrace();
        }


        createDate(_sondages,allDates);
    }*/


 /*    private void createDate(Sondages sondages, List<Dates> dates){

       sondages.setMesDates(dates);
        manager.persist(sondages);

        List<Dates> datesList = new ArrayList<Dates>();
        //date choisir user 1
        datesList.add(dates.get(0));
        datesList.add(dates.get(1));
        datesList.add(dates.get(2));
        choixDateUser(allUtilisateurs.get(1),datesList);

        //date choisir user 2
        datesList = new ArrayList<Dates>();
        datesList.add(dates.get(0));
        datesList.add(dates.get(1));
        choixDateUser(allUtilisateurs.get(2),datesList);

        //date choisir user 3
        datesList = new ArrayList<Dates>();
        datesList.add(dates.get(0));
        datesList.add(dates.get(2));
        choixDateUser(allUtilisateurs.get(3),datesList);

        validerDate(_sondages,dates.get(0));


    }*/

   /* private void choixDateUser(UtilisateursService utilisateur, List<Dates> dates){

//        for (Dates date: dates
//             ) {
//            date.addUtilisateurs(utilisateur);
//            manager.persist(date);
//
//        }



    }

    private void validerDate(Sondages sondages, Dates dates){
        dates.setEstValider(true);

        informerParticipant(sondages);
    }

    private void informerParticipant(Sondages reunion){
        int idReunion = reunion.getId();
        List<Dates> resultList = manager.createQuery("Select d From Dates d where d.reunion = :reunion_id and d.estValider=true" ).setParameter("reunion_id",reunion).getResultList();
        System.out.println("datechoisir :" + resultList.get(0).getMesUtilisateurs().size());
        for (UtilisateursService utilisateur:resultList.get(0).getMesUtilisateurs()) {
            Participants participants = new Participants(utilisateur,reunion);
            manager.persist(participants);
        }

    }





    private void listerDates() {
        List<Dates> resultList = manager.createQuery("Select u From Dates u", Dates.class).getResultList();
        System.out.println("num of date:" + resultList.size());
        for (Dates next : resultList) {
            System.out.println("next date: " + next);
        }
    }



    private void suppressionDate() {
        Dates date1 = allDates.get(1);
        manager.remove(date1);

        Dates date2 = allDates.get(2);
        manager.remove(date2);

        Dates date0 = allDates.get(0);
        manager.remove(date0);

    }

    private void suppressionReunion() {
        Sondages reunion = manager.find(Sondages.class, _sondages.getId());
        System.out.println("taille = " + reunion.toString());
        manager.getTransaction().begin();
        manager.remove(reunion);
        manager.getTransaction().commit();

    }



    public List<rest.UtilisateursService> listUtilisateurs() {
        List<UtilisateursService> resultList = manager.createQuery("Select u From UtilisateursService u", UtilisateursService.class).getResultList();
        System.out.println("num of Utilisateur:" + resultList.size());
        for (UtilisateursService next : resultList) {
            System.out.println("next Utilisateur: " + next);
        }
    }
*/

}
