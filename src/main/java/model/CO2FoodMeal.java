package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2foodmeal", schema = "public")
public class CO2FoodMeal {

    @Id
    @Column(name = "musername")
    private String musername;

    @Column(name = "typemeal")
    private String typeOfMeal;

    @Column(name = "size")
    private int size;

    protected CO2FoodMeal() {}

    /**
     * Makes a CO2FoodMeal object with the provided username, type of meal, and size of the meal
     * @param musername username of the user
     * @param typeOfMeal type of the meal the user ate
     * @param size the size of the meal
     */

    public CO2FoodMeal(String musername, String typeOfMeal, int size) {
        this.musername = musername;
        this.typeOfMeal = typeOfMeal;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("CO2FoodMeal[musername='%s', typeOfMeal='%s', size='%s']",
                musername, typeOfMeal, size);
    }

    public String getMusername() { return musername; }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
