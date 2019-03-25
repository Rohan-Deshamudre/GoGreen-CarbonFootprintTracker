package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.communication.CO2Response;
import org.junit.jupiter.api.Test;

class CO2ResponseTest {

    @Test
    void emptyCO2ResponseTest() {
        CO2Response res = new CO2Response();

        assertEquals(0, res.getNewCarbonfootprint());
        assertEquals(0, res.getOldCarbonfootprint());
        assertFalse(res.getResult());
    }

    @Test
    void randomCO2ResponseTest() {
        CO2Response res = new CO2Response();
        res.setNewCarbonfootprint(123);
        res.setOldCarbonfootprint(6969);
        res.setResult(true);

        assertEquals(123, res.getNewCarbonfootprint());
        assertEquals(6969, res.getOldCarbonfootprint());
        assertTrue(res.getResult());
    }

    @Test
    void toStringTest() {
        CO2Response res = new CO2Response();
        res.setNewCarbonfootprint(123);
        res.setOldCarbonfootprint(6969);
        res.setResult(true);

        assertEquals("\n===CO2Response===\n"
                + "   success: " + true
                + "\n   oldCarbonfootprint: " + 6969
                + "\n   newCarbonfootprint: " + 123
                + "\n", res.toString());
    }
}