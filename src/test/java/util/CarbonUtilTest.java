package util;

import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarbonUtilTest {

    @Test
    public void getFoodCarbonfootprintTest() {

    }

    @Test
    public void getTransportCarbonfootprintTest() {
        assertEquals(CarbonUtil.getTransportCarbonfootprint(1, 2),52);
    }





}
