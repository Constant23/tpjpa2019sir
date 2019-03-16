package entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "idPropositions")
public class DatesLieus extends Propositions {

    private Date date;

    private String lieu;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
