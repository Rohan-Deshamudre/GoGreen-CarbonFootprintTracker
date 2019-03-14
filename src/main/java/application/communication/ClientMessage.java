package application.communication;

public abstract class ClientMessage extends Message {

    protected String username;
    protected String password;
    //Later we'll use a session ID instead of username / password

    public ClientMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ClientMessage () {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
