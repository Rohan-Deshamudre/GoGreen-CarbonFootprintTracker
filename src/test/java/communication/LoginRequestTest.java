package communication;


import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import application.communication.LoginData;
import application.communication.LoginRequest;
import org.junit.jupiter.api.Test;

class LoginRequestTest {

    LoginData loginData = new LoginData("user", "pwd");

    @Test
    public void constructorTest() {
        LoginRequest req = new LoginRequest(loginData);
        assertTrue(req.getLoginData().equals(loginData));
    }

    @Test
    public void constructorTestEmpty() {
        LoginRequest req = new LoginRequest();
        assertNull(req.getLoginData());
    }
    @Test
    public void toStringTest() {
        LoginRequest req = new LoginRequest(loginData);
        assertEquals(req.toString(),"<LoginData{\n    username: user\n    password: pwd\n}>");
    }

    @Test
    public void toStringTestNull() {
        LoginRequest req = new LoginRequest();
        assertEquals(req.toString(), "<LoginData{\n    username: \n    password: \n}>");
    }
}