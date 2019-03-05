package application;

public class User {

     int username;
     int firstname;
     int lastname;

    public User(int username, int firstname, int lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getFirstname() {
        return firstname;
    }

    public void setFirstname(int firstname) {
        this.firstname = firstname;
    }

    public int getLastname() {
        return lastname;
    }

    public void setLastname(int lastname) {
        this.lastname = lastname;
    }

}
