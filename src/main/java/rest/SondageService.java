package rest;

import DAO.SondageDAO;
import DAO.UtilisateurDAO;
import entities.Dates;
import entities.Lieus;
import entities.Sondages;
import entities.Utilisateurs;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Path("/sondage")
public class SondageService extends HttpServlet {

    SondageDAO sdao = new SondageDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sondages> getAllSondages() {
        List<Sondages> resultList = sdao.findAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sondages getSondageById(@PathParam("id") int id) {
        return sdao.findById(id);
    }

    @GET
    @Path("/messondage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sondages> getMesSondage(@PathParam("id") int id) {
        return sdao.findByMesSondage(id);
    }

    @POST
    @Path("/addSondageDate")
    @Produces(MediaType.APPLICATION_JSON)
    public Sondages addSondage(JSONObject sondageJson) {
        System.out.println(sondageJson);
        Sondages sondage = new Sondages();
        try {
            List<Dates> dates = new ArrayList<Dates>();

            JSONArray mesPropositions = sondageJson.getJSONArray("mesPropositions");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            for(int i=0; i<mesPropositions.length(); i++){
                Dates d = new Dates();
                JSONObject row = (JSONObject) mesPropositions.get(i);
                d.setEstValider(row.getBoolean("estValider"));
                d.setaUnDejeuner(row.getBoolean("aUnDejeuner"));
                d.setDate(new Date(sdf.parse(row.getString("date")).getTime()));
                dates.add(d);
            }

           sondage = sdao.addSondageDates(sondageJson.getString("intitule"),
                    sondageJson.getString("resume"),sondageJson.getInt("idResponsable"),dates);
       } catch (Exception e) {
            e.printStackTrace();
        }
        return sondage;
    }



    @POST
    @Path("/addSondageLieu")
    @Produces(MediaType.APPLICATION_JSON)
    public Sondages addSondageLieu(JSONObject sondageJson) {
        Sondages sondage = new Sondages();
        try {
            List<Lieus> lieux = new ArrayList<Lieus>();

            JSONArray mesPropositions = sondageJson.getJSONArray("mesPropositions");

            for(int i=0; i<mesPropositions.length(); i++){
                Lieus l = new Lieus();
                JSONObject row = (JSONObject) mesPropositions.get(i);
                l.setEstValider(row.getBoolean("estValider"));
                l.setaUnDejeuner(row.getBoolean("aUnDejeuner"));
                l.setLieu(row.getString("lieu"));
                lieux.add(l);
            }

            sondage = sdao.addSondageLieu(sondageJson.getString("intitule"),
                    sondageJson.getString("resume"),sondageJson.getInt("idResponsable"),lieux);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sondage;
    }

    /*@POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateurs updateUtilisateur(JSONObject user) {
        Utilisateurs utilisateur = new Utilisateurs();
        try {
            utilisateur = udao.update(user.getInt("id"), user.getString("nom"),user.getString("prenom"),user.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUtilisateurById(@PathParam("id") int id) {
        udao.deleteById(id);
    }*/
}
