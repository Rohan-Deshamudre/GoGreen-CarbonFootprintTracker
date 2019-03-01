package communication;

public class LoginRequest extends ClientMessage {

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "<LoginRequest{" +
                "\n    username: " + username +
                "\n    password: " + password +
                "\n}>";
    }
}
