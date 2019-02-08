package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reunions {
    private int id;

    private String intitule;

    private String resume;

    private String lienInscription;

    private String lienRapport;

    private Utilisateurs responsable;

    public Reunions() {
    }

    public Reunions(int id, String intitule, String resume) {
        this.id = id;
        this.intitule = intitule;
        this.resume = resume;
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
    public Utilisateurs getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateurs responsable) {
        this.responsable = responsable;
    }
}
