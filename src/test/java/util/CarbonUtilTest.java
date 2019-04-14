package util;

import static junit.framework.TestCase.assertEquals;

import gogreen.application.communication.AddTransportRequest;
import gogreen.application.util.CarbonUtil;
import org.junit.jupiter.api.Test;

public class CarbonUtilTest {

    @Test
    public void getFoodCarbonReductionTest() {
        assertEquals(CarbonUtil.getFoodCarbonReduction("salad", 0), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("salad", 1), 300);
        assertEquals(CarbonUtil.getFoodCarbonReduction("salad", 2), 500);
        assertEquals(CarbonUtil.getFoodCarbonReduction("salad", 3), 700);

        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegetarian Meat",0), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegetarian Meat",1), 740);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegetarian Meat",2), 980);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegetarian Meat",3), 1220);

        assertEquals(CarbonUtil.getFoodCarbonReduction("Fruit",0), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Fruit",1), 140);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Fruit",2), 210);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Fruit",3), 280);

        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegan Meal",0), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegan Meal",1), 900);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegan Meal",2), 1150);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Vegan Meal",3), 1350);

        assertEquals(CarbonUtil.getFoodCarbonReduction("Other",0), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Other",1), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Other",2), 0);
        assertEquals(CarbonUtil.getFoodCarbonReduction("Other",3), 0);
    }

    @Test
    public void getLocalProduceCarbonReductionTest() {
        assertEquals(CarbonUtil.getLocalProduceCarbonReduction(10, true), 20);
        assertEquals(CarbonUtil.getLocalProduceCarbonReduction(10, false), 16);
    }

    @Test
    public void getTransportCarbonReductionTest() {
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest
                .TravelType.BIKE, 10), 2680);
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest
                .TravelType.PUB_TRANSPORT, 10), 1830);
        assertEquals(CarbonUtil.getTransportCarbonReduction(AddTransportRequest
                .TravelType.DEFAULT, 42), -1);
    }

    @Test
    public void getHomeTempCarbonReductionTest() {
        assertEquals(CarbonUtil.getHomeTempCarbonReduction(10, 4), 1600);
    }

    @Test
    public void getSolarPanelCarbonReduction() {
        assertEquals(CarbonUtil.getSolarPanelCarbonReduction(10, 3), 582);
    }
}