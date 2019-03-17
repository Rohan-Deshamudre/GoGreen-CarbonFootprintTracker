package model;


import application.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void setUsernameTest() {
        User set = new User("user", "pwd");
        set.setUsername("newusername");
        assertEquals(set.getUsername(),"newusername");
    }

    @Test
    public void getUsernameTest() {
        User get = new User("user", "pwd");
        assertEquals(get.getUsername(),"user");
    }

    @Test
    public void toStringTest() {
        User user = new User("user", "pwd");
        assertThat(user.toString().equals("<User[\n    username: user\n    password: pwd\n]>"));
    }
}
