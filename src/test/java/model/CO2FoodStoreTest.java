package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CO2FoodStoreTest {
    @Test
    public void CO2FoodStoreTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.getSusername().equals("username"));
        assertEquals(store.getWeight(), 1);
        assertTrue(store.isOrganic());
        assertEquals(store.getAmountco2(), 2);
    }


    @Test
    public void toStringTest() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.toString().equals("CO2FoodStore[susername='username', weight='1', organic='true', amountco2='2']"));
    }


    @Test
    public  void getSusernameTest() {
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
    public void getWeight() {
        CO2FoodStore store = new CO2FoodStore("username", 1 ,true, 2);
        assertEquals(store.getWeight(), 1);
    }


    @Test
    public void setWeight() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true,2);
        store.setWeight(2);
        assertEquals(store.getWeight(), 2);
    }


    @Test
    public void isOrganic() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertTrue(store.isOrganic());
    }


    @Test
    public void setOrganic() {
        CO2FoodStore store = new CO2FoodStore("username", 1 ,true, 2);
        store.setOrganic(false);
        assertFalse(store.isOrganic());
    }


    @Test
    public void getAmountco2() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        assertEquals(store.getAmountco2(), 2);
    }


    @Test
    public void setAmountco2() {
        CO2FoodStore store = new CO2FoodStore("username", 1, true, 2);
        store.setAmountco2(3);
        assertEquals(store.getAmountco2(), 3);
    }
}
