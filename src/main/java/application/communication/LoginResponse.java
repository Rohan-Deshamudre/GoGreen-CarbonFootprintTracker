package application.communication;

public class LoginResponse extends ServerMessage {

    private boolean success;

    public LoginResponse() {}

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    @Override
    public String toString() {
        return "<LoginResponse{"
                + "\n    success: " + success
                + "\n}>";
    }
}
