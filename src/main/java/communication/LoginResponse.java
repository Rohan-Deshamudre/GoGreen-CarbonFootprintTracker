package communication;

public class LoginResponse extends ServerMessage {

    private boolean success;

    /**
     * A message that contains a boolean to let the client know if a loginRequest succeeded.
     * @param success loginRequest succeeded true or false
     */
    public LoginResponse(boolean success) {
        this.success = success;
    }

    /**
     * The default constructor.
     */
    public LoginResponse() {

    }

    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String toString() {
        return "\n===LoginResponse===\n"
                + "   success: " + success
                + "\n";
    }
}
