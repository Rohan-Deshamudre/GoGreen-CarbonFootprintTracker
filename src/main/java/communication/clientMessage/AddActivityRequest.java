package communication.clientMessage;

import communication.ClientMessage;
import communication.LoginData;

public class AddActivityRequest extends ClientMessage {

    public AddActivityRequest(LoginData loginData) {
        super(loginData);
    }

    public AddActivityRequest() {
    }

    @Override
    public String toString() {
        return "\n===LoginRequest===\n" + this.loginData.toString();
    }
}