package gogreen.application.communication;

public class LoginResponse extends ServerMessage {

    /**
     * The default constructor.
     */
    public LoginResponse() {

    }

    public boolean isSuccess() {
        return getErrorMessage() == null;
    }

    @Override
    public String toString() {
        return "\n===LoginResponse===\n"
                + "   success: " + isSuccess()
                + "\n";
    }
}
