package util;

import static junit.framework.TestCase.assertEquals;

import gogreen.application.communication.AddTransportRequest;
import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Test;

public class CarbonUtilTest {

    @Test
    public void getFoodCarbonReductionTest() {
        assertEquals(CarbonUtil.getFoodCarbonReduction("salad"), 100);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegetarian Meat"), 200);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Fruit"), 80);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Else"), 150);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Other"), 0);
    }

    @Test
    public void getLocalProduceCarbonReductionTest() {
        assertEquals(CarbonUtil.getLocalProduceCarbonReduction(10, true), 23);
        assertEquals(CarbonUtil.getLocalProduceCarbonReduction(10, false), 17);
    }

    @Test
    public void getTransportCarbonReductionTest() {
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest.TravelType.BIKE, 10), 50);
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest.TravelType.PUB_TRANSPORT, 10), 20);
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest.TravelType.DEFAULT, 42), -1);
    }

    @Test
    public void getHomeTempCarbonReductionTest() {
        assertEquals(CarbonUtil.getHomeTempCarbonReduction(10, 4), 12);
    }

    @Test
    public void getSolarPanelCarbonReduction() {
        assertEquals(CarbonUtil.getSolarPanelCarbonReduction(10, 3), 12);
    }
}