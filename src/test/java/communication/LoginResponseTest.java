package communication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import application.communication.LoginResponse;
import org.junit.jupiter.api.Test;

class LoginResponseTest {

    @Test
    public void constructorTest() {
        LoginResponse resTrue = new LoginResponse(true);
        LoginResponse resFalse = new LoginResponse(false);

        assertTrue(resTrue.getSuccess());
        assertFalse(resFalse.getSuccess());
    }

    @Test
    public void constructorTestEmpty() {
        LoginResponse resEmpty = new LoginResponse();

        assertFalse(resEmpty.getSuccess());
    }

    @Test
    public void toStringTest() {
        LoginResponse resTrue = new LoginResponse(true);
        assertEquals(resTrue.toString(), "<LoginResponse{\n    success: true\n}>");

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "<LoginResponse{\n    success: false\n}>");
    }
}