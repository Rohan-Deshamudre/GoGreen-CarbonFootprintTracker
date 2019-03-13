package communication;

public abstract class ClientMessage extends Message {

    protected LoginData loginData;

    public ClientMessage(LoginData loginData) {
        this.loginData = loginData;
    }

    public ClientMessage() {
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientMessage that = (ClientMessage) o;

        return this.loginData.equals(that.loginData);
    }

}
