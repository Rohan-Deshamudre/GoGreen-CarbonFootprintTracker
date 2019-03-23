package gogreen.application.communication;

public class LoginRequest extends ClientMessage {

    /**
     * A LoginRequest Message for now is just a simple clientMessage.
     * It only contains the users credentials for authentication.
     * @param loginData The users credentials.
     */
    public LoginRequest(LoginData loginData) {
        super(loginData);
    }

    /**
     * The default constructor.
     */
    public LoginRequest() {
    }

    @Override
    public String toString() {
        if (loginData == null) {
            loginData = new LoginData("", "");
        }
        return "\n===LoginRequest===\n"
                + this.loginData.toString();
    }
}
