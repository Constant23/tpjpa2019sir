package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Dates extends Reunions {

    private Date date;

    private boolean estValider;

    private boolean aUnDejeuner;

    private Reunions reunion;

    private List<Utilisateurs> mesUtilisateurs = new ArrayList<Utilisateurs>();

    public Dates() {
    }

    public Dates(Date date, boolean estValider, boolean aUnDejeuner, Reunions reunion) {
        this.date = date;
        this.estValider = estValider;
        this.aUnDejeuner = aUnDejeuner;
        this.reunion = reunion;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEstValider() {
        return estValider;
    }

    public void setEstValider(boolean estValider) {
        this.estValider = estValider;
    }

    public boolean isaUnDejeuner() {
        return aUnDejeuner;
    }

    public void setaUnDejeuner(boolean aUnDejeuner) {
        this.aUnDejeuner = aUnDejeuner;
    }

    @ManyToOne
    public Reunions getReunion() {
        return reunion;
    }

    public void setReunion(Reunions reunion) {
        this.reunion = reunion;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    public List<Utilisateurs> getMesUtilisateurs() {
        return mesUtilisateurs;
    }

    public void setMesUtilisateurs(List<Utilisateurs> mesUtilisateurs) {
        this.mesUtilisateurs = mesUtilisateurs;
    }

    public void addUtilisateurs(Utilisateurs utilisateur) {
        mesUtilisateurs.add(utilisateur);
        utilisateur.getMesDates().add(this);
    }

    @Override
    public String toString() {
        return "Dates{" +
                ", date=" + date +
                '}';
    }
}
