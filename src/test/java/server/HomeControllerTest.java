package server;

import application.server.HomeController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {
    @Test
    public void homeTest() {
        HomeController hc = new HomeController();
        assertEquals(hc.home(), "Howdy client!");
    }
}
