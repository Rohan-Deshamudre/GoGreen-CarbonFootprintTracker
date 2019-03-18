package communication;

import application.communication.LoginResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    public void constructorTest() {
        LoginResponse resTrue = new LoginResponse(true);
        LoginResponse resFalse = new LoginResponse(false);

        assertTrue(resTrue.isSuccess());
        assertFalse(resFalse.isSuccess());
    }

    @Test
    public void constructorTestEmpty() {
        LoginResponse resEmpty = new LoginResponse();

        assertFalse(resEmpty.isSuccess());
    }
    @Test
    public void toStringTest() {
        LoginResponse resTrue = new LoginResponse(true);
        assertEquals(resTrue.toString(),"<LoginResponse{\n    success: true\n}>");

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "<LoginResponse{\n    success: false\n}>");
}
}