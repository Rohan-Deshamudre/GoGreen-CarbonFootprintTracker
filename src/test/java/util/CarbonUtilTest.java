package util;

import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarbonUtilTest {

    @Test
    void getDefaultFoodCarbonfootprints() {
        Assertions.assertEquals(100, CarbonUtil.getFoodCarbonfootprint("salad"));
        assertEquals(200, CarbonUtil.getFoodCarbonfootprint("vegetarian meat"));
        assertEquals(80, CarbonUtil.getFoodCarbonfootprint("fruit"));
        assertEquals(150, CarbonUtil.getFoodCarbonfootprint("else"));
        assertEquals(0, CarbonUtil.getFoodCarbonfootprint("not a valid checkbox value"));
    }

    @Test
    void getTransportCarbonfootprint() {
        assertEquals(5 * 3 + 50, CarbonUtil.getTransportCarbonfootprint(5, 3));
    }
}