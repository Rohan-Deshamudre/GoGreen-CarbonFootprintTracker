package server;

import communication.LoginData;
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
        LoginData req = new LoginData("user", "pwd");
        LoginController mc = new LoginController();
        LoginResponse res = new LoginResponse(false);
        assertEquals(mc.handleLoginRequest(req).toString(), "<200 OK OK,<LoginResponse{\n" +
                "    success: false\n" +
                "}>,[]>");

        LoginData req1 = new LoginData("John", "Wick");
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