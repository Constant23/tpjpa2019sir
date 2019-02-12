package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reunions {
    private int id;

    private String intitule;

    private String resume;

    private String lienInscription;

    private String lienRapport;

    private Utilisateurs leResponsable;

    private List<Dates> mesDates = new ArrayList<Dates>();

    private List<Participants> mesParticipants = new ArrayList<Participants>();

    public Reunions() {
    }

    public Reunions(String intitule, String resume, Utilisateurs utilisateurs) {
        this.intitule = intitule;
        this.resume = resume;
        this.leResponsable = utilisateurs;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLienInscription() {
        return lienInscription;
    }

    public void setLienInscription(String lienInscription) {
        this.lienInscription = lienInscription;
    }

    public String getLienRapport() {
        return lienRapport;
    }

    public void setLienRapport(String lienRapport) {
        this.lienRapport = lienRapport;
    }

    @ManyToOne
    public Utilisateurs getLeResponsable() {
        return leResponsable;
    }

    public void setLeResponsable(Utilisateurs leResponsable) {
        this.leResponsable = leResponsable;
    }

    @OneToMany(mappedBy = "reunion", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    public List<Dates> getMesDates() {
        return mesDates;
    }

    public void setMesDates(List<Dates> mesDates) {
        this.mesDates = mesDates;
    }

    @OneToMany(mappedBy = "reunion", cascade = CascadeType.REMOVE)
    public List<Participants> getMesParticipants() {
        return mesParticipants;
    }

    public void setMesParticipants(List<Participants> mesParticipants) {
        this.mesParticipants = mesParticipants;
    }

    @Override
    public String toString() {
        return "Reunions{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", resume='" + resume + '\'' +
                ", lienInscription='" + lienInscription + '\'' +
                ", lienRapport='" + lienRapport + '\'' +
                ", leResponsable=" + leResponsable +
                '}';
    }
}
