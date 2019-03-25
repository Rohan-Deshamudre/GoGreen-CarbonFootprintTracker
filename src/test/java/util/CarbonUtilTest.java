package util;

import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarbonUtilTest {

    @Test
    public void getFoodCarbonfootprintTest() {
        assertEquals(CarbonUtil.getFoodCarbonfootprint("salad"), 100);
        assertEquals(CarbonUtil.getFoodCarbonfootprint("Vegetarian Meat"), 200);
        assertEquals(CarbonUtil.getFoodCarbonfootprint("Fruit"), 80);
        assertEquals(CarbonUtil.getFoodCarbonfootprint("Else"), 150);
    }

    @Test
    public void getTransportCarbonfootprintTest() {
        assertEquals(CarbonUtil.getTransportCarbonfootprint(1, 2),52);
    }





}
