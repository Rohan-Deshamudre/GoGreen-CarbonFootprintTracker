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

    protected CO2() {
    }

    /**
     * Makes a CO2 object with the provided parameters.
     *
     * @param cusername    the username of the user.
     * @param co2food      the total CO2 reduced by diet.
     * @param co2transport the total CO2 reduced by transport.
     * @param co2energy    the total CO2 reduced by energy.
     * @param co2reduc     the total CO2 reduced by the user.
     */
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
            "CO2[cusername='%s', Co2Food='%s', Co2Transport='%s', "
                + "Co2Energy='%s', Co2Reduction='%d']",
            cusername, co2food, co2transport, co2energy, co2reduc);
    }

    public String getCUsername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public int getCO2food() {
        return co2food;
    }

    public void setCO2food(int co2food) {
        this.co2food = co2food;
    }

    /**
     * Adds more CO2 to the total reduction by food.
     *
     * @param co2Food - amount of CO2 to add.
     */
    public void addCO2Food(int co2Food) {
        this.co2food += co2Food;
    }

    public int getCO2transport() {
        return co2transport;
    }

    public void setCO2transport(int co2transport) {
        this.co2transport = co2transport;
    }

    /**
     * Adds more CO2 to the total reduction by transport.
     *
     * @param co2Transport - amount of CO2 to add.
     */
    public void addCO2Transport(int co2Transport) {
        this.co2transport += co2Transport;
    }

    public int getCO2energy() {
        return co2energy;
    }

    public void setCO2energy(int co2energy) {
        this.co2energy = co2energy;
    }

    /**
     * Adds more CO2 to the total reduction by energy usage.
     *
     * @param co2Energy - amount of CO2 to add.
     */
    public void addCO2Energy(int co2Energy) {
        this.co2energy += co2Energy;
    }

    public int getCO2reduc() {
        return co2reduc;
    }

    public void setCO2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }

    /**
     * Adds more CO2 to the total reduction.
     *
     * @param co2Reduc - the amount of CO2 to add to the total.
     */
    public void addCO2Reduc(int co2Reduc) {
        this.co2reduc += co2Reduc;
    }
}
