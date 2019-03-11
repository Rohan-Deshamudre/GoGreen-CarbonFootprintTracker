package communication.clientMessage;

import communication.ClientMessage;
import communication.LoginData;

public class GetDataRequest extends ClientMessage {

    public GetDataRequest(LoginData loginData) {
        super(loginData);
    }

    public GetDataRequest() {
    }

    @Override
    public String toString() {
        return "\n===GetDataRequest===\n" + this.loginData.toString();
    }
}