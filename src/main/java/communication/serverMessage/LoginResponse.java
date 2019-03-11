package communication.serverMessage;

import communication.ServerMessage;

public class LoginResponse extends ServerMessage {

    private boolean success;

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public LoginResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "\n===LoginResponse===\n" +
                "Success = " + this.success + ".\n";
    }
}