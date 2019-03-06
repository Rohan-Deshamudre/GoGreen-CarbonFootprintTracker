package Database.model;

import javax.persistence.*;

@Entity
@Table(name = "CO2")
public class CO2 {

    @Column(name = "cuserame")
    private String cusername;

    @Column(name = "salt")
    private int co2reduc;

    protected CO2() {}

    public CO2(String cusername) {
        this.cusername = cusername;
    }

    @Override
    public String toString() {
        return String.format(
                "CO2[cusername='%s', Co2Reduction='%d']",
                cusername,co2reduc);
    }
}
