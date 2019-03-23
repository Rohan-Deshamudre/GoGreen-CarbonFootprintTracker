package model;

import gogreen.application.model.CO2EnergySolarPanels;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CO2EnergySolarPanelsTest {

    @Test
    public void CO2EnergySolarPanelsConstructorTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        assertTrue(solar.getSpusername().equals("username"));
        assertEquals(solar.getArea(), 1);
        assertEquals(solar.getSunlightamount(), 2);
    }

    @Test
    public void toStringTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        assertThat(solar.toString().equals("CO2EnergySolarPanels[spusername='username', area='1', sunlight='2']"));
    }

    @Test
    public void getSpusernameTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        assertTrue(solar.getSpusername().equals("username"));
    }

    @Test
    public void setSpusernameTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        solar.setSpusername("username1");
        assertTrue(solar.getSpusername().equals("username1"));
    }

    @Test
    public void getAreaTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        assertEquals(solar.getArea(), 1);
    }

    @Test
    public void setAreaTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        solar.setArea(2);
        assertEquals(solar.getArea(), 2);
    }

    @Test
    public void getSunlightamountTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        assertEquals(solar.getSunlightamount(), 2);
    }

    @Test
    public void setSunlightamountTest() {
        CO2EnergySolarPanels solar = new CO2EnergySolarPanels("username", 1, 2);
        solar.setSunlightamount(3);
        assertEquals(solar.getSunlightamount(), 3);
    }
}
