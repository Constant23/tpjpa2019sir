package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Utilisateurs {
    private int id;

    private String nom;

    private String prenom;

    private String email;

    @ManyToMany(mappedBy="mesUtilisateurs")
    private Collection<Propositions> mesPropositions ;

    private List<Sondages> mesSondages = new ArrayList<Sondages>();

    private List<Participants> mesParticipations = new ArrayList<Participants>();

    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @OneToMany(mappedBy = "leResponsable", cascade = CascadeType.REMOVE)
    public List<Sondages> getMesSondages() {
        return mesSondages;
    }

    public void setMesSondages(List<Sondages> mesSondages) {
        this.mesSondages = mesSondages;
    }

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    public List<Participants> getMesParticipations() {
        return mesParticipations;
    }

    public void setMesParticipations(List<Participants> mesParticipations) {
        this.mesParticipations = mesParticipations;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
