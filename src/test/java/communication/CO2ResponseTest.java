package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gogreen.application.communication.CO2Response;
import org.junit.jupiter.api.Test;

class CO2ResponseTest {

    @Test
    void emptyCO2ResponseTest() {
        CO2Response res = new CO2Response();

        assertEquals(0, res.getCO2Reduction());
    }

    @Test
    void randomCO2ResponseTest() {
        CO2Response res = new CO2Response(123);

        assertEquals(123, res.getCO2Reduction());
    }
}