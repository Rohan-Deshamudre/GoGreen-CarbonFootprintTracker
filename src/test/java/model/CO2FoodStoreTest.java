package model;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import gogreen.application.model.CO2FoodStore;
import org.junit.jupiter.api.Test;

public class CO2FoodStoreTest {

    @Test
    public void co2FoodStoreConstructorTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.getSusername().equals("username"));
        assertEquals(store.getWeight(), 1);
        assertTrue(store.isOrganic());
        assertEquals(store.getAmountco2(), 2);
    }


    @Test
    public void toStringTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.toString().equals("CO2FoodStore[susername='username', weight='1', "
            + "organic='true', amountco2='2']"));
    }


    @Test
    public void getSusernameTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.getSusername().equals("username"));
    }


    @Test
    public void setSusernameTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        store.setSusername("username1");
        assertTrue(store.getSusername().equals("username1"));
    }


    @Test
    public void getWeightTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertEquals(store.getWeight(), 1);
    }


    @Test
    public void setWeightTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        store.setWeight(2);
        assertEquals(store.getWeight(), 2);
    }


    @Test
    public void isOrganicTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.isOrganic());
    }


    @Test
    public void setOrganicTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        store.setOrganic(false);
        assertFalse(store.isOrganic());
    }


    @Test
    public void getAmountco2Test() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertEquals(store.getAmountco2(), 2);
    }


    @Test
    public void setAmountco2Test() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        store.setAmountco2(3);
        assertEquals(store.getAmountco2(), 3);
    }
}
