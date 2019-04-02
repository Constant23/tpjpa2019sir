package entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "idPropositions")
public class Lieus extends Propositions{

    private static final long serialVersionUID = 1L;

    private String lieu;

    public Lieus(String lieu, Propositions propositions) {
        super(propositions.isEstValider(),propositions.isaUnDejeuner());
        this.lieu = lieu;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
