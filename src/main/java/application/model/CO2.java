package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2", schema = "public")
public class CO2 {

    @Id
    @Column(name = "cusername")
    private String cusername;

    @Column(name = "co2reduc")
    private int co2reduc;

    public void setCo2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }

    public int getCo2reduc() {
        return co2reduc;
    }

    protected CO2() {}

    public CO2(String cusername, int co2reduc) {
        this.cusername = cusername;
        this.co2reduc=co2reduc;
    }

    public String getCusername() {
        return cusername;
    }

    public int getCo2reduc() {
        return co2reduc;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public void setCo2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }

    @Override
    public String toString() {
        return String.format(
                "CO2[cusername='%s', Co2Reduction='%d']",
                cusername,co2reduc);
    }
}
