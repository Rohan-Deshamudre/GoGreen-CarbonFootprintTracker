package communication;

public class LoginData {

    private String username;
    private String password;

    /**
     * The general constructor
     * @param username
     * @param password
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginData loginData = (LoginData) o;

        if (!this.username.equals(loginData.username)) return false;
        return this.password.equals(loginData.password);
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
