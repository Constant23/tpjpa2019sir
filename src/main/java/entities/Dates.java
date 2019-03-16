package entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "idPropositions")
public class Dates extends Propositions{

    private static final long serialVersionUID = 1L;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
