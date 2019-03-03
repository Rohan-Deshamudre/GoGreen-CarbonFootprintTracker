package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginResponseTest {

    @Test
    public void constructorTest() {
        LoginResponse resTrue = new LoginResponse(true);
        LoginResponse resFalse = new LoginResponse(false);

        assertEquals(resTrue.getSuccess(),true);
        assertEquals(resFalse.getSuccess(), false);
    }

    @Test
    public void toStringTest() {
        LoginResponse resTrue = new LoginResponse(true);
        assertEquals(resTrue.toString(), "<LoginResponse{\n    success: true\n}>");

        LoginResponse resFalse = new LoginResponse(false);
        assertEquals(resFalse.toString(), "<LoginResponse{\n    success: false\n}>");
    }
}