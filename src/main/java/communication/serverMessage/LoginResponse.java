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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResponse that = (LoginResponse) o;

        return success == that.success;
    }

    @Override
    public String toString() {
        return "\n===LoginResponse===\n" +
                "Success: " + this.success + ".\n";
    }
}