package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.ShowUser;
import org.junit.jupiter.api.Test;

public class ShowUserTest {

    @Test
    public void FriendConstructorTest() {
        ShowUser user = new ShowUser("user", 1);
        assertTrue(user.getUsername().equals("user"));
        assertEquals(user.getCo2reduc(), 1);
    }

    @Test
    public void toStringTest() {
        ShowUser user = new ShowUser("user", 1);
        assertTrue(user.toString().equals("ShowUser[username='user', co2reduc='1']"));
    }

    @Test
    public void getUsernameTest() {
        ShowUser user = new ShowUser("user", 1);
        assertTrue(user.getUsername().equals("user"));
    }

    @Test
    public void setFusernameTest() {
        ShowUser user = new ShowUser("user", 1);
        user.setUsername("user1");
        assertTrue(user.getUsername().equals("user1"));
    }

    @Test
    public void getFriendTest() {
        ShowUser user = new ShowUser("user", 1);
        assertEquals(user.getCo2reduc(), 1);
    }

    @Test
    public void setFriendTest() {
        ShowUser user = new ShowUser("user", 1);
        user.setCo2reduc(2);
        assertEquals(user.getCo2reduc(), 2);
    }
}
