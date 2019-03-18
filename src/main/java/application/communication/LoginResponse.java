package application.communication;

public class LoginResponse extends ServerMessage {

    private boolean success;

    /**
     * This class needs to have a default constructor.
     * Otherwise resttemplate.postForObject(...) does not work.
     */
    public LoginResponse() {}

    /**
     * A message that contains a boolean to let the client know if a loginRequest succeeded
     * @param success loginRequest succeeded true or false
     */
    public LoginResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String toString() {
        return "<LoginResponse{"
                + "\n    success: " + success
                + "\n}>";
    }
}
