package server;

import application.communication.LoginRequest;
import application.communication.LoginResponse;
import application.server.LoginController;
import application.server.MirrorController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {
    public static ResponseEntity<String> responseBuilder(String res){
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Test
    public void statusTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        LoginController mc = new LoginController();
        LoginResponse res = new LoginResponse(false);
        assertEquals(mc.handleLoginRequest(req).toString(), "<200 OK OK,<LoginResponse{\n" +
                "    success: false\n" +
                "}>,[]>");

        LoginRequest req1 = new LoginRequest("John", "Wick");
        LoginController mc1 = new LoginController();
        LoginResponse res1 = new LoginResponse(true);
        assertEquals(mc.handleLoginRequest(req1).toString(), "<200 OK OK,<LoginResponse{\n" +
                "    success: true\n" +
                "}>,[]>");
    }

    @Test
    public void mirrorTest() {
        MirrorController mc = new MirrorController();
        assertEquals(mc.mirror("{1231}"), responseBuilder("{1231}"));
    }
}