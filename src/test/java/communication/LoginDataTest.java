package communication;

import communication.clientMessage.LoginRequest;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class LoginDataTest {

    @Test
    void constructorTest() {
        LoginData login = new LoginData("user", "pwd");
        assertEquals("user", login.getUsername());
        assertEquals("pwd", login.getPassword());
    }

    @Test
    void constructorTestEmpty() {
        LoginData login = new LoginData();
        assertNull(login.getUsername());
        assertNull(login.getPassword());
    }

    @Test
    void getUsername() {
        LoginData login = new LoginData("user", "pwd");
        assertEquals("user", login.getUsername());
    }

    @Test
    void setUsername() {
        LoginData login = new LoginData("user", "pwd");
        login.setUsername("changed");
        assertEquals("changed", login.getUsername());
    }

    @Test
    void getPassword() {
        LoginData login = new LoginData("user", "pwd");
        assertEquals("pwd", login.getPassword());
    }

    @Test
    void setPassword() {
        LoginData login = new LoginData("user", "pwd");
        login.setPassword("changed");
        assertEquals("changed", login.getPassword());
    }
    @Test
    void equalsTest() {
        LoginData req1 = new LoginData("user", "password");
        LoginData req2 = new LoginData("user", "password");
        LoginData req3 = new LoginData("invalid", "invalid");
        LoginData req4 = new LoginData();
        assertTrue(req1.equals(req1));
        assertTrue(req1.equals(req2));
        assertFalse(req1.equals(req3));
        assertFalse(req1.equals(req4));
        assertTrue(req4.equals(req4));
    }
    @Test
    void toStringTest() {
        LoginData req = new LoginData("user", "pwd");
        assertEquals(req.toString(),"<LoginData{\n    username: user\n    password: pwd\n}>");
    }

    @Test
    void toStringTestNull() {
        LoginData req = new LoginData(null, null);
        assertEquals(req.toString(), "<LoginData{\n    username: \n    password: \n}>");
    }
}