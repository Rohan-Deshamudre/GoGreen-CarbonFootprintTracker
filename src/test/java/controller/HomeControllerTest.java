package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.controller.HomeController;
import org.junit.jupiter.api.Test;

public class HomeControllerTest {

    @Test
    public void homeTest() {
        assertTrue(HomeController.home().equals("Please Log In:"));
    }
}
