package communication.clientMessage;

import communication.ClientMessage;
import communication.LoginData;

public class LoginRequest extends ClientMessage {

    public LoginRequest(LoginData loginData) {
        super(loginData);
    }

    public LoginRequest() {
    }

    @Override
    public String toString() {
        return "\n===LoginRequest===\n" + this.loginData.toString();
    }
}
