package communication;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import gogreen.application.communication.LoginResponse;
import org.junit.jupiter.api.Test;

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
        assertEquals("\n===LoginResponse===\n   success: true\n", resTrue.toString());

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "\n===LoginResponse===\n   success: false\n");
    }
}