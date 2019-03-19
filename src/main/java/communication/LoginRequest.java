package communication;

public class LoginRequest extends ClientMessage {

    private LoginData loginData;

    public LoginRequest(LoginData loginData) {
        this.loginData = loginData;
    }

    /**
     * This class needs to have a default constructor.
     * Otherwise resttemplate.postForObject(...) does not work.
     */
    public LoginRequest() {
    }

    public LoginData getLoginData() {
        return loginData;
    }

    @Override
    public String toString() {
        if (loginData == null) {
            loginData = new LoginData("", "");
        }
        return this.loginData.toString();
    }
}
