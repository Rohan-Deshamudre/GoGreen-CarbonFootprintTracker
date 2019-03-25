package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.model.CO2EnergyTemperature;
import org.junit.jupiter.api.Test;

public class CO2EnergyTemperatureTest {

    @Test
    public void CO2EnergyTemperatureConstructorTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        assertTrue(tem.getTusername().equals("username"));
        assertEquals(tem.getReduction(), 1);
        assertEquals(tem.getDuration(), 2);
    }

    @Test
    public void toStringTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        assertThat(tem.toString().equals("CO2EnergyTemperature[tusername='username', reduction='1', duration='2']"));
    }

    @Test
    public void getTusernameTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        assertTrue(tem.getTusername().equals("username"));
    }

    @Test
    public void setTusernameTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        tem.setTusername("username1");
        assertTrue(tem.getTusername().equals("username1"));
    }

    @Test
    public void getReductionTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        assertEquals(tem.getReduction(), 1);
    }

    @Test
    public void setReductionTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        tem.setReduction(2);
        assertEquals(tem.getReduction(), 2);
    }

    @Test
    public void getDurationTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        assertEquals(tem.getDuration(), 2);
    }

    @Test
    public void setDurationTest() {
        CO2EnergyTemperature tem = new CO2EnergyTemperature("username", 1, 2);
        tem.setDuration(3);
        assertEquals(tem.getDuration(), 3);
    }
}
