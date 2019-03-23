package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CO2FoodMealTest {

    @Test
    public void CO2FoodMealTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 2);
        assertTrue(meal.getMusername().equals("username"));
        assertTrue(meal.getTypeOfMeal().equals("type"));
        assertEquals(meal.getSize(), 2);
    }


    @Test
    public void toStringTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        assertTrue(meal.toString().equals("CO2FoodMeal[musername='username', typeOfMeal='type', size='1']"));
    }


    @Test
    public void getMusernameTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        assertTrue(meal.getMusername().equals("username"));
    }


    @Test
    public void setMusernameTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        meal.setMusername("username1");
        assertTrue(meal.getMusername().equals("username1"));
    }


    @Test
    public void getTypeOfMealTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        assertTrue(meal.getTypeOfMeal().equals("type"));
    }


    @Test
    public void setTypeOfMealTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        meal.setTypeOfMeal("type1");
        assertTrue(meal.getTypeOfMeal().equals("type1"));
    }


    @Test
    public void getSizeTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        assertEquals(meal.getSize(), 1);
    }


    @Test
    public void setSizeTest() {
        CO2FoodMeal meal = new CO2FoodMeal("username", "type", 1);
        meal.setSize(2);
        assertEquals(meal.getSize(), 2);
    }
}
