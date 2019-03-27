package gogreen.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CO2", schema = "public")
public class CO2 {

    @Id
    @Column(name = "cusername")
    private String cUsername;

    @Column(name = "CO2food")
    private int CO2Food;

    @Column(name = "CO2transport")
    private int CO2Transport;

    @Column(name = "CO2energy")
    private int CO2Energy;

    @Column(name = "CO2reduc")
    private int CO2Reduc;

    protected CO2() {
    }

    /**
     * Makes a CO2 object with the provided parameters.
     *
     * @param cUsername the username of the user.
     * @param CO2Food the total CO2 reduced by diet.
     * @param CO2Transport the total CO2 reduced by transport.
     * @param CO2Energy the total CO2 reduced by energy.
     * @param CO2Reduc the total CO2 reduced by the user.
     */
    public CO2(String cUsername, int CO2Food, int CO2Transport, int CO2Energy, int CO2Reduc) {
        this.cUsername = cUsername;
        this.CO2Food = CO2Food;
        this.CO2Transport = CO2Transport;
        this.CO2Energy = CO2Energy;
        this.CO2Reduc = CO2Reduc;
    }

    @Override
    public String toString() {
        return String.format(
            "CO2[cusername='%s', Co2Food='%s', Co2Transport='%s', "
                + "Co2Energy='%s', Co2Reduction='%d']",
            cUsername, CO2Food, CO2Transport, CO2Energy, CO2Reduc);
    }

    public String getCUsername() {
        return cUsername;
    }

    public int getCO2Food() {
        return CO2Food;
    }

    public void addCO2Food(int CO2Food) {
        this.CO2Food += CO2Food;
    }

    public int getCO2Transport() {
        return CO2Transport;
    }

    public int getCO2Energy() {
        return CO2Energy;
    }

    public int getCO2Reduc() {
        return CO2Reduc;
    }

    /**
     * Adds a more CO2 to the total reduction.
     * @param CO2Reduc - the amount of CO2 to add to the total.
     */
    public void addCO2Reduc(int CO2Reduc) {
        this.CO2Reduc += CO2Reduc;
    }
}
