package communication;

public abstract class ClientMessage extends Message {

    protected LoginData loginData;

    /**
     * Abstract Class ClientMessage serves the purpose of making sure every
     * ClientMessage contains LoginData. Later if we want to we can look into
     * more secure ways to authenticate a clientMessage (with sessions or JWT)
     * but for now we just resend username and password.
     * @param loginData the username and password
     */
    public ClientMessage(LoginData loginData) {
        this.loginData = loginData;
    }

    public ClientMessage() {
    }

    public LoginData getLoginData() {
        return loginData;
    }

}
