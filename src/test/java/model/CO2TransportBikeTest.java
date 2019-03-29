package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.model.CO2TransportBike;
import org.junit.jupiter.api.Test;

public class CO2TransportBikeTest {

    @Test
    public void co2TransportBikeConstructorTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        assertTrue(bike.getBusername().equals("username"));
        assertEquals(bike.getDistance(), 1);
        assertEquals(bike.getTimesaweek(), 2);
    }

    @Test
    public void toStringTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        assertThat(bike.toString()
            .equals("CO2TransoirtBike[busername='username', distance='1', timesaweek='2']"));
    }

    @Test
    public void getBusernameTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        assertTrue(bike.getBusername().equals("username"));
    }

    @Test
    public void setBusernameTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        bike.setBusername("username1");
        assertTrue(bike.getBusername().equals("username1"));
    }

    @Test
    public void getDistanceTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        assertEquals(bike.getDistance(), 1);
    }

    @Test
    public void setDistanceTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        bike.setDistance(2);
        assertEquals(bike.getDistance(), 2);
    }

    @Test
    public void getTimesaweekTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        assertEquals(bike.getTimesaweek(), 2);
    }

    @Test
    public void setTimesaweekTest() {
        CO2TransportBike bike = new CO2TransportBike("username", 1, 2);
        bike.setTimesaweek(3);
        assertEquals(bike.getTimesaweek(), 3);
    }
}
