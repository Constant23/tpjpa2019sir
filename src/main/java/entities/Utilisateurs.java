package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilisateurs {
    private int id;

    private String nom;

    private String prenom;

    private String email;

    private List<Dates> mesDates = new ArrayList<Dates>();

    private List<Reunions> mesReunions = new ArrayList<Reunions>();

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

    @ManyToMany (mappedBy = "mesUtilisateurs", cascade = CascadeType.REMOVE)
    public List<Dates> getMesDates() {
        return mesDates;
    }

    public void setMesDates(List<Dates> mesDates) {
        this.mesDates = mesDates;
    }

    @OneToMany(mappedBy = "leResponsable", cascade = CascadeType.REMOVE)
    public List<Reunions> getMesReunions() {
        return mesReunions;
    }

    public void setMesReunions(List<Reunions> mesReunions) {
        this.mesReunions = mesReunions;
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
