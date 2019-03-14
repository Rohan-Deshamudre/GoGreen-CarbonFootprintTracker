package communication.clientMessage;

import communication.ClientMessage;
import communication.LoginData;

public class AddActivityRequest extends ClientMessage {

    /**
     * Not in use yet
     * @param loginData LoginData to authenticate the sender.
     */
    public AddActivityRequest(LoginData loginData) {
        super(loginData);
    }

    /**
     * default constructor for Spring reasons I do not understand
     */
    public AddActivityRequest() {
        super();
    }

    @Override
    public String toString() {
        return "\n===LoginRequest===\n" + this.loginData.toString();
    }
}