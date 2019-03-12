package server;

import communication.LoginData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static server.ServerApplication.checkLoginData;

class ServerApplicationTest {

    @Test
    public void checkLoginDataTest() {
        assertTrue(checkLoginData("user", "password"));
        assertFalse(checkLoginData("user", "Cena"));
        assertFalse(checkLoginData("Steve", "password"));
    }

    @Test
    public void checkLoginRequestTest() {
        LoginData req1 = new LoginData("user", "password");
        LoginData req2 = new LoginData("user", "Cena");
        LoginData req3 = new LoginData("Steve", "password");
        assertTrue(checkLoginData(req1));
        assertFalse(checkLoginData(req2));
        assertFalse(checkLoginData(req3));
    }
}