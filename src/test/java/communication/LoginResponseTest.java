package communication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

<<<<<<< HEAD
import application.communication.LoginResponse;
=======
import communication.serverMessage.LoginResponse;
>>>>>>> 0d5abd2f7a41f69bc01f4eaa959e899835294e7a
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
        assertEquals(resTrue.toString(), "\n===LoginResponse===\n" +
                "Success: true.\n");

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "\n===LoginResponse===\n" +
                "Success: false.\n");
}
}