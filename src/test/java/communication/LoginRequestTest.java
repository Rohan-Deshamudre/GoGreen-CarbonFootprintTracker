package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import application.communication.LoginRequest;
import org.junit.jupiter.api.Test;

class LoginRequestTest {

    @Test
    public void constructorTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        assertEquals(req.getUsername(),"user");
        assertEquals(req.getPassword(),"pwd");
    }

    @Test
    public void constructorTestEmpty() {
        LoginRequest req = new LoginRequest();
        assertNull(req.getUsername());
        assertNull(req.getPassword());
    }

    @Test
    public void toStringTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        assertEquals(req.toString(),"<LoginRequest{\n    username: user\n    password: pwd\n}>");
    }

    @Test
    public void toStringTestNull() {
        LoginRequest req = new LoginRequest(null, null);
        assertEquals(req.toString(), "<LoginRequest{\n    username: \n    password: \n}>");
    }
}