package entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "idPropositions")
public class Dates extends Propositions{

    private static final long serialVersionUID = 1L;

    private Date date;

    public Dates() {

    }

    public Dates(Date date, boolean estValider, boolean aUnDejeuner, Sondages sondages) {
        super(estValider,aUnDejeuner);
        super.setMonSondage(sondages);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
