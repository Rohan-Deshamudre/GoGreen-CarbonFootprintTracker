package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Test;

class CarbonUtilTest {

    @Test
    void getDefaultFoodCarbonfootprints() {
        assertEquals(100, CarbonUtil.getFoodCarbonReduction("salad"));
        assertEquals(200, CarbonUtil.getFoodCarbonReduction("vegetarian meat"));
        assertEquals(80, CarbonUtil.getFoodCarbonReduction("fruit"));
        assertEquals(150, CarbonUtil.getFoodCarbonReduction("else"));
        assertEquals(0, CarbonUtil.getFoodCarbonReduction("not a valid checkbox value"));
    }

    @Test
    void getTransportCarbonfootprint() {
        assertEquals(5 * 3 + 50, CarbonUtil.getTransportCarbonReduction(5, 3));
    }
}
