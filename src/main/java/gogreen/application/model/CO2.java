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
    private String cusername;

    @Column(name = "CO2food")
    private int CO2food;

    @Column(name = "CO2transport")
    private int CO2transport;

    @Column(name = "CO2energy")
    private int CO2energy;

    @Column(name = "CO2reduc")
    private int CO2reduc;

    protected CO2() {
    }

    /**
     * Makes a CO2 object with the provided parameters.
     *
     * @param cusername    the username of the user.
     * @param CO2food      the total CO2 reduced by diet.
     * @param CO2transport the total CO2 reduced by transport.
     * @param CO2energy    the total CO2 reduced by energy.
     * @param CO2reduc     the total CO2 reduced by the user.
     */
    public CO2(String cusername, int CO2food, int CO2transport, int CO2energy, int CO2reduc) {
        this.cusername = cusername;
        this.CO2food = CO2food;
        this.CO2transport = CO2transport;
        this.CO2energy = CO2energy;
        this.CO2reduc = CO2reduc;
    }

    @Override
    public String toString() {
        return String.format(
            "CO2[cusername='%s', Co2Food='%s', Co2Transport='%s', "
                + "Co2Energy='%s', Co2Reduction='%d']",
            cusername, CO2food, CO2transport, CO2energy, CO2reduc);
    }

    public String getCUsername() {
        return cusername;
    }

    public int getCO2food() {
        return CO2food;
    }

    /**
     * Adds more CO2 to the total reduction by food.
     *
     * @param co2Food - amount of CO2 to add.
     */
    public void addCO2Food(int co2Food) {
        this.CO2food += co2Food;
    }

    public int getCO2transport() {
        return CO2transport;
    }

    /**
     * Adds more CO2 to the total reduction by transport.
     *
     * @param co2Transport - amount of CO2 to add.
     */
    public void addCO2Transport(int co2Transport) {
        this.CO2transport += co2Transport;
    }

    public int getCO2energy() {
        return CO2energy;
    }

    /**
     * Adds more CO2 to the total reduction by energy usage.
     *
     * @param co2Energy - amount of CO2 to add.
     */
    public void addCO2Energy(int co2Energy) {
        this.CO2energy += co2Energy;
    }

    public int getCO2reduc() {
        return CO2reduc;
    }

    /**
     * Adds more CO2 to the total reduction.
     *
     * @param co2Reduc - the amount of CO2 to add to the total.
     */
    public void addCO2Reduc(int co2Reduc) {
        this.CO2reduc += co2Reduc;
    }
}
