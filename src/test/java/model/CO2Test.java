package model;

import application.model.CO2;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class CO2Test {
    @Test
    public void setCusername() {
        CO2 user = new CO2("user", 10);
        user.setCusername("user1");
        assertTrue("user1".equals(user.getCusername()));
    }

    @Test
    public void getCusername(){
        CO2 user = new CO2("user", 10);
        assertTrue("user".equals(user.getCusername()));
    }

    @Test
    public void setCo2reducTest() {
        CO2 user = new CO2("user", 10);
        user.setCo2reduc(11);
        assertEquals(11, user.getCo2reduc());
    }

    @Test
    public void getCo2reducTest() {
        CO2 user = new CO2("user", 10);
        assertEquals(10, user.getCo2reduc());
    }
    @Test
    public void toStringTest() {
        CO2 user = new CO2("user", 10);
        assertTrue(user.toString().equals("CO2[cusername='user', Co2Reduction='10']"));
    }
}

