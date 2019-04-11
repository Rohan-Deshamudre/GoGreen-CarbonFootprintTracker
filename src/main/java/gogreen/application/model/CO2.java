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
    private int co2Transport;

    @Column(name = "co2energy")
    private int co2Energy;

    @Column(name = "co2reduc")
    private int co2Reduc;

    @Column(name = "achievement")
    private String achievement;

    protected CO2() {
    }

    /**
     * Makes a CO2 object with the provided parameters.
     *
     * @param cusername    the username of the user.
     * @param co2Food      the total CO2 reduced by diet.
     * @param co2Transport the total CO2 reduced by transport.
     * @param co2Energy    the total CO2 reduced by energy.
     * @param co2Reduc     the total CO2 reduced by the user.
     * @param achievement  achievements of the user.
     */
    public CO2(String cusername, int co2Food, int co2Transport, int co2Energy, int co2Reduc,
               String achievement) {
        this.cusername = cusername;
        this.co2food = co2Food;
        this.co2Transport = co2Transport;
        this.co2Energy = co2Energy;
        this.co2Reduc = co2Reduc;
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return String.format(
            "CO2[cusername='%s', Co2Food='%s', Co2Transport='%s', "
                + "Co2Energy='%s', Co2Reduction='%d', achievement='%s']",
            cusername, co2food, co2Transport, co2Energy, co2Reduc, achievement);
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
        return co2Transport;
    }

    public void setCO2transport(int co2transport) {
        this.co2Transport = co2transport;
    }

    /**
     * Adds more CO2 to the total reduction by transport.
     *
     * @param co2Transport - amount of CO2 to add.
     */
    public void addCO2Transport(int co2Transport) {
        this.co2Transport += co2Transport;
    }

    public int getCO2energy() {
        return co2Energy;
    }

    public void setCO2energy(int co2energy) {
        this.co2Energy = co2energy;
    }

    /**
     * Adds more CO2 to the total reduction by energy usage.
     *
     * @param co2Energy - amount of CO2 to add.
     */
    public void addCO2Energy(int co2Energy) {
        this.co2Energy += co2Energy;
    }

    public int getCO2reduc() {
        return co2Reduc;
    }

    public void setCO2reduc(int co2reduc) {
        this.co2Reduc = co2reduc;
    }

    /**
     * Adds more CO2 to the total reduction.
     *
     * @param co2Reduc - the amount of CO2 to add to the total.
     */
    public void addCO2Reduc(int co2Reduc) {
        this.co2Reduc += co2Reduc;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }
}
