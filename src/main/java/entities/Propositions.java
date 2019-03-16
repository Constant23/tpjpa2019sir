package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Propositions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean estValider;

    private boolean aUnDejeuner;

    @ManyToMany
    private Collection<Utilisateurs> mesUtilisateurs ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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




    @Override
    public String toString() {
        return "Propositions{" +
                "id=" + id +
                ", estValider=" + estValider +
                ", aUnDejeuner=" + aUnDejeuner +
                '}';
    }
}
