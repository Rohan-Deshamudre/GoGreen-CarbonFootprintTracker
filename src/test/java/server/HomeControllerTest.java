package server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gogreen.application.controller.HomeController;
import org.junit.jupiter.api.Test;

public class HomeControllerTest {
    @Test
    public void homeTest() {
        HomeController hc = new HomeController();
        assertEquals(hc.home(), "Please Log In:");
    }
}
