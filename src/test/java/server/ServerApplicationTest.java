package server;

import communication.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static server.ServerApplication.checkLoginData;

class ServerApplicationTest {

    @Test
    public void checkLoginDataTest() {
       assertTrue(checkLoginData("Jhon", "Wick"));
       assertFalse(checkLoginData("Jhon", "Cena"));
       assertFalse(checkLoginData("Steve", "Wick"));
    }

    @Test
    public void checkLoginRequestTest() {
        LoginRequest req1 = new LoginRequest("Jhon", "Wick");
        LoginRequest req2 = new LoginRequest("Jhon", "Cena");
        LoginRequest req3 = new LoginRequest("Steve", "Wick");
        assertTrue(checkLoginData(req1));
        assertTrue(checkLoginData(req2));
        assertTrue(checkLoginData(req3));
    }
}