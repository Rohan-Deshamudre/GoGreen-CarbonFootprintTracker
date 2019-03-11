package Database.model;

import javax.persistence.*;

@Entity
@Table(name = "CO2")
public class CO2 {

    @Column(name = "cusername")
    private String cusername;

    @Column(name = "co2reduc")
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
