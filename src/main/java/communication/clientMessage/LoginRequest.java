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
        if(loginData == null) {
            LoginData emptyLogindata = new LoginData();
            return "\n===LoginRequest===\n" + emptyLogindata.toString();
        }
        return "\n===LoginRequest===\n" + this.loginData.toString();
    }
}
