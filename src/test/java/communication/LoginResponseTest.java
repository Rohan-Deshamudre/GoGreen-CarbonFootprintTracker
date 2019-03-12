package communication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import communication.serverMessage.LoginResponse;
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
    public void equalsTest() {
        LoginResponse req1 = new LoginResponse(true);
        LoginResponse req2 = new LoginResponse(true);
        LoginResponse req3 = new LoginResponse(false);
        LoginResponse req4 = new LoginResponse();
        assertTrue(req1.equals(req1));
        assertTrue(req1.equals(req2));
        assertFalse(req1.equals(req3));
        assertFalse(req1.equals(req4));
        assertTrue(req4.equals(req4));
    }
    @Test
    public void toStringTest() {
        LoginResponse resTrue = new LoginResponse(true);
        assertEquals(resTrue.toString(), "\n===LoginResponse===\n" +
                "Success: true.\n");

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "\n===LoginResponse===\n" +
                "Success: false.\n");
}
}