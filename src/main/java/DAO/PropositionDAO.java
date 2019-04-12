package DAO;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Dates;
import entities.Lieus;
import entities.Utilisateurs;
import jpa.EntityManagerHelper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


public class PropositionDAO {
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();

    public Dates addDates(Dates dates) {
        manager.persist(dates);
        return dates;
    }

    public Lieus addLieux(Lieus lieus) {
        manager.persist(lieus);
        return lieus;
    }

}
