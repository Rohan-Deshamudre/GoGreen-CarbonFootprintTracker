package gogreen.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friendlist", schema = "public")
public class Friend {

    @Column(name = "fusername")
    private String fusername;

    @Column(name = "friend")
    private String friend;

    protected Friend() {}

    public Friend(String fusername, String friend) {
        this.fusername = fusername;
        this.friend = friend;
    }

    public String toString() {
        return String.format("Friend[fusername='%s', friend='%s']",
                fusername, friend);
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
