package communication;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import gogreen.application.communication.ErrorMessage;
import gogreen.application.communication.LoginResponse;
import org.junit.jupiter.api.Test;

class LoginResponseTest {

    @Test
    public void constructorTest() {
        LoginResponse res = new LoginResponse();
        res.setErrorMessage(new ErrorMessage(ErrorMessage.LOGIN_WRONG_PASS));

        assertFalse(res.isSuccess());
    }

    @Test
    public void constructorTestEmpty() {
        LoginResponse resEmpty = new LoginResponse();
        assertTrue(resEmpty.isSuccess());
    }

    @Test
    public void toStringTest() {
        LoginResponse resTrue = new LoginResponse();
        assertEquals("\n===LoginResponse===\n   success: true\n", resTrue.toString());

        LoginResponse resFalse = new LoginResponse();
        resFalse.setErrorMessage(new ErrorMessage(ErrorMessage.LOGIN_WRONG_PASS));
        assertEquals(resFalse.toString(), "\n===LoginResponse===\n   success: false\n");
    }
}