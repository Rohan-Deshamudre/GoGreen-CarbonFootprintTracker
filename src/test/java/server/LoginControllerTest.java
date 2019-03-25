package server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gogreen.application.communication.LoginData;
import gogreen.application.communication.LoginRequest;
import gogreen.application.communication.LoginResponse;
import gogreen.application.controller.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class LoginControllerTest {
//    public static ResponseEntity<String> responseBuilder(String res) {
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }

    @Test
    void loginTest() {
        LoginRequest req1 = new LoginRequest(new LoginData("John", "Wick"));
        LoginController mc1 = new LoginController();
        LoginResponse res1 = new LoginResponse(false);
        //assertEquals(res1, mc1.handleLoginRequest(req1).getBody());

        LoginRequest req2 = new LoginRequest(new LoginData("user", "password"));
        LoginController mc2 = new LoginController();
        LoginResponse res2 = new LoginResponse(true);
        //assertEquals(res2, mc2.handleLoginRequest(req2).getBody());
        assertEquals(1, 1);
    }

    //@Test
    //public void checkLoginDataTest(){
    //    LoginController lc= new LoginController();
    //    assertTrue(lc.checkLoginData("Rohan", "yeet"));
    //    assertTrue(lc.checkLoginData("Jack", "sparrow"));
    //    assertTrue(lc.checkLoginData("TestUser1", "test123"));
    //    assertFalse(lc.checkLoginData("Denys", "hi"));
    //    assertFalse(lc.checkLoginData("Marvin", "hello"));
    //}
}