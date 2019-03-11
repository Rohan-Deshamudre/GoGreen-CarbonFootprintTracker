package communication;

public class LoginData extends ClientMessage {

    private String username;
    private String password;

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This class needs to have a default constructor.
     * Otherwise resttemplate.postForObject(...) does not work.
     */
    public LoginData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        return "<LoginData{"
                + "\n    username: " + username
                + "\n    password: " + password
                + "\n}>";
    }
}
