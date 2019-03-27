package gogreen.application.communication;

public class ErrorMessage extends Exception {

    public static final String LOGIN_WRONG_USER = "Username not found in the database";
    public static final String LOGIN_WRONG_PASS = "Invalid username/password combination";
    public static final String REG_USER = "Username is already taken";
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
