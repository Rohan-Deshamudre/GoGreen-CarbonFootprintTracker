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
}
