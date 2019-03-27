package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.CO2List;
import org.junit.jupiter.api.Test;

public class CO2ListTest {

    @Test
    public void CO2ListConstructorTest() {
        CO2List list = new CO2List("user", 1);
        assertTrue(list.getUsername().equals("user"));
        assertEquals(list.getCo2reduc(), 1);
    }

    @Test
    public void toStringTest() {
        CO2List list = new CO2List("user", 1);
        assertTrue(list.toString().equals("CO2List[username='user', co2reduc='1']"));
    }

    @Test
    public void getUsernameTest() {
        CO2List list = new CO2List("user", 1);
        assertTrue(list.getUsername().equals("user"));
    }

    @Test
    public void setUsernameTest() {
        CO2List list = new CO2List("user", 1);
        list.setUsername("user1");
        assertTrue(list.getUsername().equals("user1"));
    }

    @Test
    public void getCo2reducTest() {
        CO2List list = new CO2List("user", 1);
        assertEquals(list.getCo2reduc(), 1);
    }

    @Test
    public void setCo2reducTest() {
        CO2List list = new CO2List("user", 1);
        list.setCo2reduc(2);
        assertEquals(list.getCo2reduc(), 2);
    }
}
