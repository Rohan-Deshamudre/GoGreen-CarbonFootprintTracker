package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.AddTransportRequest.TravelType;
import gogreen.application.communication.LoginData;
import org.junit.jupiter.api.Test;

class AddTransportRequestTest {

    @Test
    void constructorTest() {
        AddTransportRequest tr = new AddTransportRequest(
                new LoginData(), TravelType.BIKE, 3);
        assertEquals(20, tr.getDistance());
        assertEquals(TravelType.BIKE, tr.getTravelType());
    }

    @Test
    void emptyConstructorTest() {
        AddTransportRequest tr = new AddTransportRequest();
        assertEquals(0, tr.getDistance());
        assertNull(tr.getTravelType());
    }

    @Test
    void toStringTest() {
        AddTransportRequest tr = new AddTransportRequest(
                new LoginData(), TravelType.PUB_TRANSPORT, 3);
        assertEquals("<Co2Request{"
                + "\n    distance: " + 20
                + "\n    type: " + TravelType.PUB_TRANSPORT
                + "\n}>", tr.toString());
    }
}