package communication.clientMessage;

import communication.ClientMessage;
import communication.LoginData;

public class LoginRequest extends ClientMessage {
    /**
     * A simple loginrequest message that the client sends to the server that contains login data.
     *
     * @param loginData LoginData to authenticate the sender.
     */
    public LoginRequest(LoginData loginData) {
        super(loginData);
    }

    /**
     * default constructor for Spring reasons I do not understand
     */
    public LoginRequest() {
        super();
    }

    @Override
    public String toString() {
        if (loginData == null) {
            LoginData emptyLogindata = new LoginData();
            return "\n===LoginRequest===\n" + emptyLogindata.toString();
        }
        return "\n===LoginRequest===\n" + this.loginData.toString();
    }


}
