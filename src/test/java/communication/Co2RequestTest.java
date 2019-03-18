package communication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Co2RequestTest {
    @Test
    public void Co2RequestTest() {
        Co2Request req = new Co2Request("choice", "user");
        assertTrue(req.getChoiceBoxValue().equals("choice"));
        assertTrue(req.getCusername().equals("user"));
    }

    @Test
    public void getChoiceBoxValue() {
        Co2Request req = new Co2Request("choice", "user");
        assertTrue(req.getChoiceBoxValue().equals("choice"));
    }

    @Test
    public void setChoiceBoxValue() {
        Co2Request req = new Co2Request("choice", "user");
        req.setChoiceBoxValue("choice2");
        assertTrue(req.getChoiceBoxValue().equals("choice2"));
    }

    @Test
    public void getCusername() {
        Co2Request req = new Co2Request("choice", "user");
        assertTrue(req.getCusername().equals("user"));
    }

    @Test
    public void setCusername() {
        Co2Request req = new Co2Request("choice", "user");
        req.setCusername("user2");
        assertTrue(req.getCusername().equals("user2"));
    }
}
