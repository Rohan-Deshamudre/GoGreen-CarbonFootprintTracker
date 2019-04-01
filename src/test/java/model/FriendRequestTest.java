package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gogreen.application.model.FriendRequest;
import org.junit.jupiter.api.Test;

public class FriendRequestTest {

    @Test
    public void FriendRequestConstructorTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        assertEquals(request.getId(), 1);
        assertTrue(request.getUsername().equals("username"));
        assertTrue(request.getRequestTo().equals("username1"));
    }

    @Test
    public void toStringTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        assertTrue(request.toString().equals("FriendRequest[id='1', username='username', requestTo='username1']"));
    }

    @Test
    public void getIdTest() {
        FriendRequest request = new FriendRequest(1, "usernaame", "username1");
        assertEquals(request.getId(), 1);
    }

    @Test
    public void setIdTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        request.setId(2);
        assertEquals(request.getId(), 2);
    }

    @Test
    public void getUsernameTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        assertTrue(request.getUsername().equals("username"));
    }

    @Test
    public void setUsernameTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        request.setUsername("username2");
        assertTrue(request.getUsername().equals("username2"));
    }

    @Test
    public void getRequestTo() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        assertTrue(request.getRequestTo().equals("username1"));
    }

    @Test
    public void setRequestToTest() {
        FriendRequest request = new FriendRequest(1, "username", "username1");
        request.setRequestTo("username3");
        assertTrue(request.getRequestTo().equals("username3"));
    }
}
