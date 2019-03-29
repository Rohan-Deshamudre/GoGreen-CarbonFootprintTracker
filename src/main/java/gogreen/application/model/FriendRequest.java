package gogreen.application.model;

import javax.persistence.*;

@Entity
@Table(name = "friendrequest", schema = "public")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "requestto")
    private String requestTo;

    protected FriendRequest() {}

    public FriendRequest(int id, String username, String requestTo) {
        this.id = id;
        this.username = username;
        this.requestTo = requestTo;
    }

    public String toString() {
        return String.format("FriendRequest[id='%s', username='%s', requestTo='%s']",
                id, username, requestTo);
    }
}
