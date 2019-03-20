package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2foodstore", schema = "public")
public class CO2FoodStore {

    @Id
    @Column(name = "susername")
    private String susername;

    @Column(name = "weight")
    private int weight;

    @Column(name = "organic")
    private boolean organic;

    @Column(name = "amountco2")
    private int amountco2;

    protected CO2FoodStore() {}

    /**
     * Makes a CO2FoodStore object with the provided username, weight of the meal, whether the meal is organic or not
     * and the amount of co2 saved.
     * @param susername username of the user
     * @param weight weight of the meal
     * @param organic whether the meal is organic or not
     * @param amountco2 the amount of co2 saved
     */
    public CO2FoodStore(String susername, int weight, boolean organic, int amountco2) {
        this.susername = susername;
        this.weight = weight;
        this.organic = organic;
        this.amountco2 = amountco2;
    }

    public String toString() {
        return String.format("CO2FoodStore[susername='%s', weight='%s', organic='%s', amountco2='%s']",
                susername, weight, organic, amountco2);
    }

    public String getSusername() { return susername; }

    public void setSusername(String susername) { this.susername = susername; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public boolean isOrganic() { return organic; }

    public void setOrganic(boolean organic) { this.organic = organic; }

    public int getAmountco2() { return amountco2; }

    public void setAmountco2(int amountco2) { this.amountco2 = amountco2; }

}
