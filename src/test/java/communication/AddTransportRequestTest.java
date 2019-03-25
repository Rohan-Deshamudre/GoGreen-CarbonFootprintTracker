package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.LoginData;
import org.junit.jupiter.api.Test;

class AddTransportRequestTest {

    @Test
    void constructorTest() {
        AddTransportRequest tr = new AddTransportRequest(
                new LoginData(), 20, 3);
        assertEquals(20, tr.getDistance());
        assertEquals(3, tr.getTimesaweek());
    }

    @Test
    void emptyConstructorTest() {
        AddTransportRequest tr = new AddTransportRequest();
        assertEquals(0, tr.getDistance());
        assertEquals(0, tr.getTimesaweek());
    }

    @Test
    void toStringTest() {
        AddTransportRequest tr = new AddTransportRequest(
                new LoginData(), 20, 3);
        assertEquals("<Co2Request{"
                + "\n    distance: " + 20
                + "\n    timesaweek: " + 3
                + "\n}>", tr.toString());
    }
}