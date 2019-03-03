package communication;

public class LoginRequest extends ClientMessage {

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This class needs to have a default constructor.
     * Otherwise resttemplate.postForObject(...) does not work.
     */
    public LoginRequest() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



    @Override
    public String toString() {
        if (username == null) username = "";
        if (password == null) password = "";

        return "<LoginRequest{" +
                "\n    username: " + username +
                "\n    password: " + password +
                "\n}>";
    }
}
