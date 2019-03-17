package server;

<<<<<<< HEAD
import application.communication.LoginRequest;
import application.communication.LoginResponse;
import application.server.MirrorController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import communication.LoginData;
import communication.clientMessage.LoginRequest;
import communication.serverMessage.LoginResponse;
import org.junit.jupiter.api.Test;
>>>>>>> 0d5abd2f7a41f69bc01f4eaa959e899835294e7a

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