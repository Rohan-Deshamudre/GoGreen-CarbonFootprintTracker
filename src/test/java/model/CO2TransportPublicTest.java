package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.model.CO2TransportPublic;
import org.junit.jupiter.api.Test;

public class CO2TransportPublicTest {

    @Test
    public void CO2TransportPublicConstructorTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        assertTrue(pub.getPusername().equals("username"));
        assertEquals(pub.getDistance(), 1);
        assertEquals(pub.getTimesaweek(), 2);
    }

    @Test
    public void toStringTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 2);
        assertTrue(pub.toString().equals("CO2TransportPublic[pusername='username', distance='1', timesaweek='2']"));
    }

    @Test
    public void getPusernameTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        assertTrue(pub.getPusername().equals("username"));
    }

    @Test
    public void getDistanceTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        assertEquals(pub.getDistance(), 1);
    }

    @Test
    public void getTimesaweekTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        assertEquals(pub.getTimesaweek(), 2);
    }

    @Test
    public void setPusernameTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        pub.setPusername("username1");
        assertTrue(pub.getPusername().equals("username1"));
    }

    @Test
    public void setDistanceTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        pub.setDistance(2);
        assertEquals(pub.getDistance(), 2);
    }

    @Test
    public void setTimesaweekTest() {
        CO2TransportPublic pub = new CO2TransportPublic("username", 1, 1);
        pub.setTimesaweek(3);
        assertEquals(pub.getTimesaweek(), 3);
    }
}
