package communication;

import application.communication.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    public void constructorTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        assertEquals(req.getUsername(),"user");
        assertEquals(req.getPassword(),"pwd");
    }

    @Test
    public void toStringTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        assertEquals(req.toString(),"<LoginRequest{\n    username: user\n    password: pwd\n}>");
    }

}