package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.CO2;
import org.junit.jupiter.api.Test;


public class CO2Test {

    @Test
    public void getCUsernameTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertTrue(user.getCUsername().equals("user"));
    }

    @Test
    public void getCO2FoodTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertEquals(user.getCO2food(), 10);
    }

    @Test
    public void getCO2Transport() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertEquals(user.getCO2transport(), 11);
    }

    @Test
    public void getCO2Energy() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertEquals(user.getCO2energy(), 12);
    }

    @Test
    public void getCO2ReducTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertEquals(13, user.getCO2reduc());
    }

    @Test
    public void co2ConstructorTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertTrue(user.getCUsername().equals("user"));
        assertEquals(user.getCO2food(), 10);
        assertEquals(user.getCO2transport(), 11);
        assertEquals(user.getCO2energy(), 12);
        assertEquals(user.getCO2reduc(), 13);
        assertEquals(user.getAchievement(), "00000000000000");
    }

    @Test
    public void toStringTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13, "00000000000000");
        assertEquals(user.toString(), "CO2[cusername='user', Co2Food='10', "
            + "Co2Transport='11', Co2Energy='12', Co2Reduction='13', achievement='00000000000000']");
    }
}

