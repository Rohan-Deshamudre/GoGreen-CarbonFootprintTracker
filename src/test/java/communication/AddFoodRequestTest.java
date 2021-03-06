package communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.LoginData;
import org.junit.jupiter.api.Test;

public class AddFoodRequestTest {
    @Test
    public void constructorTest() {
        LoginData login = new LoginData("user", "psw");
        AddFoodRequest req = new AddFoodRequest(login, "choice", 2);
        assertEquals("user", req.getLoginData().getUsername());
        assertEquals("psw", req.getLoginData().getPassword());
        assertEquals("choice", req.getChoiceBoxValue());
        assertTrue(req.getAmount() == 2);
    }

    @Test
    public void constructorTestEmpty() {
        AddFoodRequest reqEmpty = new AddFoodRequest();
        assertTrue(reqEmpty.getAmount() == 0);
        assertNull(reqEmpty.getChoiceBoxValue());
        assertNull(reqEmpty.getLoginData());
    }

    @Test
    public void toStringTest() {
        LoginData login = new LoginData("user", "psw");
        AddFoodRequest req = new AddFoodRequest(login, "choice", 2);
        assertEquals("\n===AddFoodRequest===\n"
                + "   username: user\n"
                + "   password: psw\n"
                + "   choiceBoxValue: choice\n"
                + "   amount: 2\n",
                req.toString());
    }
}