package gogreen.application.model;

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

    @Column(name = "co2food")
    private int co2food;

    @Column(name = "co2transport")
    private int co2transport;

    @Column(name = "co2energy")
    private int co2energy;

    @Column(name = "co2reduc")
    private int co2reduc;

    protected CO2() {}

    public CO2(String cusername, int co2food, int co2transport, int co2energy, int co2reduc) {
        this.cusername = cusername;
        this.co2food = co2food;
        this.co2transport = co2transport;
        this.co2energy = co2energy;
        this.co2reduc = co2reduc;
    }

    @Override
    public String toString() {
        return String.format(
                "CO2[cusername='%s', Co2Food='%s', Co2Transport='%s', Co2Energy='%s', Co2Reduction='%d']",
                cusername, co2food, co2transport, co2energy, co2reduc);
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) { this.cusername = cusername; }

    public int getCo2food() { return co2food; }

    public void setCo2food(int co2food) { this.co2food = co2food; }

    public int getCo2transport() { return co2transport; }

    public void setCo2transport(int co2transport) { this.co2transport = co2transport; }

    public int getCo2energy() { return co2energy; }

    public void setCo2energy(int co2energy) { this.co2energy = co2energy; }

    public int getCo2reduc() {
        return co2reduc;
    }

    public void setCo2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }

}
