package communication;

public abstract class ClientMessage extends Message {

    protected LoginData loginData;

    /**
     * Each clientMessage should contain LoginData.
     * @param loginData The user's credentials.
     */
    public ClientMessage(LoginData loginData) {
        this.loginData = loginData;
    }

    /**
     * The default constructor.
     */
    public ClientMessage() {
    }

    /**
     * The getter for LoginData.
     * @return
     */
    public LoginData getLoginData() {
        return loginData;
    }
}
