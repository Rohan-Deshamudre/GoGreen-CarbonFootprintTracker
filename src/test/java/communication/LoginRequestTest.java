package communication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    public void constructorTest() {
        LoginRequest req = new LoginRequest("user", "pwd");
        assertEquals(req.getUsername(),"user");
        assertEquals(req.getPassword(),"pwd");
    }


}