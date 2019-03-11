package server;

import communication.LoginData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static server.ServerApplication.checkLoginData;

class ServerApplicationTest {

    @Test
    public void checkLoginDataTest() {
        assertTrue(checkLoginData("John", "Wick"));
        assertFalse(checkLoginData("John", "Cena"));
        assertFalse(checkLoginData("Steve", "Wick"));
    }

    @Test
    public void checkLoginRequestTest() {
        LoginData req1 = new LoginData("John", "Wick");
        LoginData req2 = new LoginData("John", "Cena");
        LoginData req3 = new LoginData("Steve", "Wick");
        assertTrue(checkLoginData(req1));
        assertFalse(checkLoginData(req2));
        assertFalse(checkLoginData(req3));
    }
}