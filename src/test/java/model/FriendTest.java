package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.Friend;
import org.junit.jupiter.api.Test;

public class FriendTest {

    @Test
    public void FriendConstructorTest() {
        Friend friend = new Friend(1, "user", "friend");
        assertEquals(friend.getId(), 1);
        assertTrue(friend.getFusername().equals("user"));
        assertTrue(friend.getFriend().equals("friend"));
    }

    @Test
    public void toStringTest() {
        Friend friend = new Friend(1, "user", "friend");
        assertTrue(friend.toString().equals("Friend[id='1', fusername='user', friend='friend']"));
    }

    @Test
    public void getIdTest() {
        Friend friend = new Friend(1, "user", "friend");
        assertEquals(friend.getId(), 1);
    }

    @Test
    public void setIdTest() {
        Friend friend = new Friend(1, "user", "friend");
        friend.setId(2);
        assertEquals(friend.getId(), 2);
    }

    @Test
    public void getFusernameTest() {
        Friend friend = new Friend(1, "user", "friend");
        assertTrue(friend.getFusername().equals("user"));
    }

    @Test
    public void setFusernameTest() {
        Friend friend = new Friend(1, "user", "friend");
        friend.setFusername("user1");
        assertTrue(friend.getFusername().equals("user1"));
    }

    @Test
    public void getFriendTest() {
        Friend friend = new Friend(1, "user", "friend");
        assertTrue(friend.getFriend().equals("friend"));
    }

    @Test
    public void setFriendTest() {
        Friend friend = new Friend(1, "user", "friend");
        friend.setFriend("friend1");
        assertTrue(friend.getFriend().equals("friend1"));
    }
}
