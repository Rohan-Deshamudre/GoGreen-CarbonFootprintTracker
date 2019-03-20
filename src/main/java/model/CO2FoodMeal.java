package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2foodmeal", schema = "public")
public class CO2FoodMeal {

    @Id
    @Column
    private String musername;

    @Column
    private String typeOfMeal;

    @Column
    private int size;

    protected CO2FoodMeal() {}

    /**
     * Contains the username, type of meal the user ate and the size of it.
     * @param musername username
     * @param typeOfMeal type of the meal the user ate
     * @param size how much grams the meal weights
     */

    public CO2FoodMeal(String musername, String typeOfMeal, int size) {
        this.musername = musername;
        this.typeOfMeal = typeOfMeal;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("CO2FoodMeal[musername='%s', typeOfMeal='%s', size='%s'",
                musername, typeOfMeal, size);
    }

    public String getMusername() {
        return this.musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getTypeOfMeal() {
        return this.typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }




}
