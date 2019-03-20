package communication;

public class LoginData {

    private String username;
    private String password;

    /**
     * Simple constructor for a loginData class used to authenticate messages.
     * @param username the users username
     * @param password the users password
     */
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

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        return "   username: " + username
                + "\n   password: " + password
                + "\n";
    }
}
