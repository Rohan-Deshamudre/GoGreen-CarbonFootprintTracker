package gogreen.application.model;

public class ShowUser {

    private String username;
    private int co2reduc;

    public ShowUser(String username, int co2reduc) {
        this.username = username;
        this.co2reduc = co2reduc;
    }

    public String toString() {
        return String.format("ShowUser[username='%s', co2reduc='%s']",
                username, co2reduc);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCo2reduc() {
        return co2reduc;
    }

    public void setCo2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }
}
