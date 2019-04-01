package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.FriendList;
import org.junit.jupiter.api.Test;

public class FriendListTest {

    @Test
    public void FriendListConstructorTest() {
        FriendList friend = new FriendList("friend", 1);
        assertTrue(friend.getFriend().equals("friend"));
        assertEquals(friend.getFco2(), 1);
    }

    @Test
    public void toStringTest() {
        FriendList friend = new FriendList("friend", 1);
        assertTrue(friend.toString().equals("FriendList[friend='friend', fco2='1']"));
    }

    @Test
    public void getFriendTest() {
        FriendList friend = new FriendList("friend", 1);
        assertTrue(friend.getFriend().equals("friend"));
    }

    @Test
    public void setFriendTest() {
        FriendList friend = new FriendList("friend", 1);
        friend.setFriend("friend1");
        assertTrue(friend.getFriend().equals("friend1"));
    }

    @Test
    public void getFco2Test() {
        FriendList friend = new FriendList("friend", 1);
        assertEquals(friend.getFco2(), 1);
    }

    @Test
    public void setFco2Test() {
        FriendList friend = new FriendList("friend", 1);
        friend.setFco2(2);
        assertEquals(friend.getFco2(), 2);
    }
}
