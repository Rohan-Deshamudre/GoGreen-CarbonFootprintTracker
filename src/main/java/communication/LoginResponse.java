package communication;

public class LoginResponse extends ServerMessage {

    private boolean success;

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }
}
