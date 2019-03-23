package model;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class CO2Test {
    @Test
    public void getCusernameTest(){
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertTrue(user.getCusername().equals("user"));
    }

    @Test
    public void setCusernameTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        user.setCusername("user1");
        assertTrue(user.getCusername().equals("user1"));
    }

    @Test
    public void getCo2foodTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertEquals(user.getCo2food(), 10);
    }

    @Test
    public void setCo2foodTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        user.setCo2food(11);
        assertEquals(user.getCo2food(), 11);
    }

    @Test
    public void getCo2transport() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertEquals(user.getCo2transport(), 11);
    }

    @Test
    public void setCo2transport() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        user.setCo2transport(12);
        assertEquals(user.getCo2transport(), 12);
    }

    @Test
    public void getCo2energy() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertEquals(user.getCo2energy(), 12);
    }

    @Test
    public void setCo2energy() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        user.setCo2energy(13);
        assertEquals(user.getCo2energy(), 13);
    }

    @Test
    public void getCo2reducTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertEquals(13, user.getCo2reduc());
    }

    @Test
    public void setCo2reducTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        user.setCo2reduc(14);
        assertEquals(14, user.getCo2reduc());
    }

    @Test
    public void CO2ConstructorTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertTrue(user.getCusername().equals("user"));
        assertEquals(user.getCo2food(), 10);
        assertEquals(user.getCo2transport(), 11);
        assertEquals(user.getCo2energy(), 12);
        assertEquals(user.getCo2reduc(), 13);
    }

    @Test
    public void toStringTest() {
        CO2 user = new CO2("user", 10, 11, 12, 13);
        assertTrue(user.toString().equals("CO2[cusername='user', Co2Food='10', Co2Transport='11', Co2Energy='12', Co2Reduction='13']"));
    }
}

