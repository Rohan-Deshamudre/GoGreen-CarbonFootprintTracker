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

    /**
     * Make a FriendRequest object with the provided parameters.
     * @param id the id of the request
     * @param username the username of the sender
     * @param requestTo the user in the request
     */
    public FriendRequest(int id, String username, String requestTo) {
        this.id = id;
        this.username = username;
        this.requestTo = requestTo;
    }

    @Override
    public String toString() {
        return String.format("FriendRequest[id='%s', username='%s', requestTo='%s']",
                id, username, requestTo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }
}
