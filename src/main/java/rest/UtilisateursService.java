package rest;

import DAO.UtilisateurDAO;
import entities.Utilisateurs;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/utilisateurs")
public class UtilisateursService extends HttpServlet {

    UtilisateurDAO udao = new UtilisateurDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateurs> getAllUtilisateur() {
        List<Utilisateurs> resultList = udao.findAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateurs getUtilisateurById(@PathParam("id") int id) {
        return udao.findById(id);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateurs addUtilisateur(JSONObject user) {
        Utilisateurs utilisateur = new Utilisateurs();
        try {
            utilisateur = udao.add(user.getString("nom"),user.getString("prenom"),user.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateurs getLogin(JSONObject user) {
        Utilisateurs utilisateur = new Utilisateurs();
        try {
            utilisateur = udao.getLogin(user.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @POST
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
    }
}
