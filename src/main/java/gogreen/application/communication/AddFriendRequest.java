package gogreen.application.communication;

public class AddFriendRequest extends ClientMessage {

    private String friendUsername;

    /**
     * Default constructor of the class.
     * @param loginData the login data.
     * @param friendUsername username of the friend you want to add.
     */
    public AddFriendRequest(LoginData loginData, String friendUsername) {
        super(loginData);
        this.friendUsername = friendUsername;
    }

    /**
     * To string method of the class.
     * @return a String representation of the class.
     */
    @Override
    public String toString() {
        return "<AddFriendRequest[friendUsername = " + friendUsername + "]>";
    }
}
