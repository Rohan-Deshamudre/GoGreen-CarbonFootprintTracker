package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    protected User() {}

    /**
     * The user class containing username and password.
     * @param username username of the user
     * @param password password of the user
     */

    public User(String username,String password) {
        this.username = username;
        this.password = password;

    }

    @Override
    public String toString() {
        return String.format(
                "User[username='%s', password='%s']",
                username,password);
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

}
