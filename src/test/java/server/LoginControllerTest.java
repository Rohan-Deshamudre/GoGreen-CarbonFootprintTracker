package server;

import communication.LoginData;
import communication.clientMessage.LoginRequest;
import communication.serverMessage.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    @Test
    public void loginTest() {
        LoginRequest req1 = new LoginRequest(new LoginData("John", "Wick"));
        LoginController mc1 = new LoginController();
        LoginResponse res1 = new LoginResponse(false);
        assertEquals(res1, mc1.handleLoginRequest(req1).getBody());

        LoginRequest req2 = new LoginRequest(new LoginData("user", "password"));
        LoginController mc2 = new LoginController();
        LoginResponse res2 = new LoginResponse(true);
        assertEquals(res2, mc2.handleLoginRequest(req2).getBody());
    }
}