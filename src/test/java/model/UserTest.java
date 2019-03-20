package model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void setPassword() {
        User user = new User("user", "pwd");
        user.setPassword("pwd1");
        assertTrue("pwd1".equals(user.getPassword()));
    }

    @Test
    public void getPassword() {
        User user = new User("user", "pwd");
        assertTrue("pwd".equals(user.getPassword()));
    }

    @Test
    public void setUsernameTest() {
        User set = new User("user", "pwd");
        set.setUsername("newusername");
        assertTrue(set.getUsername().equals("newusername"));
    }

    @Test
    public void getUsernameTest() {
        User get = new User("user", "pwd");
        assertTrue(get.getUsername().equals("user"));
    }

    @Test
    public void toStringTest() {
        User user = new User("user", "pwd");
        assertTrue(user.toString().equals("User[username='user', password='pwd']")); //
    }
}

