package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Participants {
    private int id;

    private String lienUnique;

    private String preference;

    private String allergies;

    private String clearCode;

    private boolean present;

    private Utilisateurs utilisateur;

    private Sondages sondage;

    public Participants() {
    }

    public Participants(Utilisateurs utilisateur, Sondages sondage) {
        this.utilisateur = utilisateur;
        this.sondage = sondage;
        this.lienUnique = "doodle.com/" + sondage.getIntitule() + "/" + utilisateur.getEmail();
        this.present = false;
        this.clearCode = "clearCode_" + utilisateur.getEmail();

    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLienUnique() {
        return lienUnique;
    }

    public void setLienUnique(String lienUnique) {
        this.lienUnique = lienUnique;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getClearCode() {
        return clearCode;
    }

    public void setClearCode(String clearCode) {
        this.clearCode = clearCode;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @ManyToOne
    public Utilisateurs getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateurs utilisateur) {
        this.utilisateur = utilisateur;
    }

    @ManyToOne
    public Sondages getSondage() {
        return sondage;
    }

    public void setSondage(Sondages sondage) {
        this.sondage = sondage;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "id=" + id +
                ", lienUnique='" + lienUnique + '\'' +
                ", preference='" + preference + '\'' +
                ", allergies='" + allergies + '\'' +
                ", clearCode='" + clearCode + '\'' +
                ", present=" + present +
                ", utilisateur=" + utilisateur +
                ", sondage=" + sondage +
                '}';
    }
}
