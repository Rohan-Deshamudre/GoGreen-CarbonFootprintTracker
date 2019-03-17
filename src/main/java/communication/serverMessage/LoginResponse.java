package communication.serverMessage;

import communication.ServerMessage;

public class LoginResponse extends ServerMessage {

    private boolean success;

    /**
     * A simple class that just contains a boolean to tell the user
     * if a login request succeeded or not.
     * @param success if the login request succeeded true or false.
     */
    public LoginResponse(boolean success) {
        this.success = success;
    }

    /**
     * default constructor for Spring reasons I do not understand
     */
    public LoginResponse() {
        super();
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "\n===LoginResponse===\n" +
                "Success: " + this.success + ".\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResponse that = (LoginResponse) o;

        return success == that.success;
    }
}