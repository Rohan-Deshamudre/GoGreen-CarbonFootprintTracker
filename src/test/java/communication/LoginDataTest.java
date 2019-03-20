package communication;

import org.junit.jupiter.api.Test;

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
    void toStringTest() {
        LoginData req = new LoginData("user", "pwd");
        assertEquals(req.toString(),"   username: user\n   password: pwd\n");
    }

    @Test
    void toStringTestNull() {
        LoginData req = new LoginData(null, null);
        assertEquals("   username: \n   password: \n", req.toString());
    }
}