package gogreen.application.model;

public class FriendList {

    private String friend;
    private int fco2;

    public FriendList(String friend, int fco2) {
        this.friend = friend;
        this.fco2 = fco2;
    }

    public String toString() {
        return String.format("FriendList[friend='%s', fco2='%s']",
                friend, fco2);
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getFco2() {
        return fco2;
    }

    public void setFco2(int fco2) {
        this.fco2 = fco2;
    }
}
