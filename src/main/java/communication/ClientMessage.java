package communication;

public abstract class ClientMessage extends Message {

    protected LoginData loginData;

    public ClientMessage(LoginData loginData) {
        this.loginData = loginData;
    }

    public ClientMessage () {
    }

    public LoginData getLoginData() {
        return loginData;
    }

}
