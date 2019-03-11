package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class LoginRequestTest {

    @Test
    public void constructorTest() {
        LoginData req = new LoginData("user", "pwd");
        assertEquals(req.getUsername(),"user");
        assertEquals(req.getPassword(),"pwd");
    }

    @Test
    public void constructorTestEmpty() {
        LoginData req = new LoginData();
        assertNull(req.getUsername());
        assertNull(req.getPassword());
    }

    @Test
    public void toStringTest() {
        LoginData req = new LoginData("user", "pwd");
        assertEquals(req.toString(),"<LoginData{\n    username: user\n    password: pwd\n}>");
    }

    @Test
    public void toStringTestNull() {
        LoginData req = new LoginData(null, null);
        assertEquals(req.toString(), "<LoginData{\n    username: \n    password: \n}>");
    }
}