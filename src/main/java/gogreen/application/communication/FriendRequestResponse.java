package gogreen.application.communication;

public class FriendRequestResponse extends ClientMessage {

    private String friendUsername;
    private boolean accepted;

    /**
     * Constructor of the class.
     * @param loginData the login data.
     * @param friendUsername username of the friend.
     * @param accepted whether the request was accepted.
     */
    public FriendRequestResponse(LoginData loginData, String friendUsername, boolean accepted) {
        super(loginData);
        this.friendUsername = friendUsername;
        this.accepted = accepted;
    }

    public FriendRequestResponse() {

    }

    /**
     * Getter of friendUsername.
     * @return friendUsername.
     */
    public String getFriendUsername() {
        return friendUsername;
    }

    /**
     * Getter.
     * @return accepted.
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * String representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "<FriendRequestResponse[friendUsername:" + friendUsername
                + ", accepted: " + accepted + "]>";
    }
}
