package server;

import application.communication.LoginRequest;
import org.junit.jupiter.api.Test;

import static ServerApplication.checkLoginData;

class ServerApplicationTest {

    @Test
    public void checkLoginDataTest() {
        assertTrue(checkLoginData("John", "Wick"));
        assertFalse(checkLoginData("John", "Cena"));
        assertFalse(checkLoginData("Steve", "Wick"));
    }

    @Test
    public void checkLoginRequestTest() {
        LoginRequest req1 = new LoginRequest("John", "Wick");
        LoginRequest req2 = new LoginRequest("John", "Cena");
        LoginRequest req3 = new LoginRequest("Steve", "Wick");
        assertTrue(checkLoginData(req1));
        assertFalse(checkLoginData(req2));
        assertFalse(checkLoginData(req3));
    }
}