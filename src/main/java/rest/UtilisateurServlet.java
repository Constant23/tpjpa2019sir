package rest;

import DAO.UtilisateurDAO;
import entities.Utilisateurs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="UtilisateurServlet",
        urlPatterns={"/UtilisateurServlet"})
public class UtilisateurServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        UtilisateurDAO udao = new UtilisateurDAO();

        try {
            udao.add(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tableauUser = "<table>";

        List<Utilisateurs> resultList = udao.findAll();
        for (Utilisateurs x : resultList) {

            tableauUser += "<tr>";
            tableauUser += "<td>" + x.getNom() + "</td>";
            tableauUser += "<td>" + x.getPrenom() + "</td>";
            tableauUser += "<td>" + x.getEmail() + "</td></tr>";

        }

        tableauUser += "</table>";


        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("nom") + "\n" +
                " <LI>Prenom: "
                + request.getParameter("prenom") + "\n" +
                " <LI>Email: "
                + request.getParameter("email") + "\n" +
                "</UL>\n" +
                        tableauUser +
                "</BODY></HTML>");




    }
}
