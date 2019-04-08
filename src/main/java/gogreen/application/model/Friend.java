package gogreen.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendlist", schema = "public")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "fusername")
    private String fusername;

    @Column(name = "friend")
    private String friend;

    protected Friend() {}

    /**
     * Makes a Friend object with the provided parameters.
     * @param id the id of the friendship
     * @param fusername the username of one of the friends
     * @param friend the username of one of the friends
     */
    public Friend(int id, String fusername, String friend) {
        this.id = id;
        this.fusername = fusername;
        this.friend = friend;
    }

    public String toString() {
        return String.format("Friend[id='%s', fusername='%s', friend='%s']",
                id, fusername, friend);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
