package rest;

import DAO.UtilisateurDAO;
import entities.Utilisateurs;
import org.codehaus.jettison.json.JSONObject;


import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/utilisateurs")
public class UtilisateursService extends HttpServlet {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateurs> getAllUtilisateur() {

        UtilisateurDAO udao = new UtilisateurDAO();
        return udao.findAll();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateurs getUtilisateurById(@PathParam("id") int id) {

        UtilisateurDAO udao = new UtilisateurDAO();
        return udao.findById(id);

    }



    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateurs> addUtilisateur(JSONObject user) {
        System.out.println(user);

        UtilisateurDAO udao = new UtilisateurDAO();

        try {
            udao.add(user.getString("nom"),user.getString("prenom"),user.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getAllUtilisateur();

    }


    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateurs> updateUtilisateur(JSONObject user) {

        UtilisateurDAO udao = new UtilisateurDAO();

        try {
            udao.update(user.getInt("id"), user.getString("nom"),user.getString("prenom"),user.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getAllUtilisateur();
    }




    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUtilisateurById(@PathParam("id") int id) {
        UtilisateurDAO udao = new UtilisateurDAO();
        udao.deleteById(id);
    }

}
