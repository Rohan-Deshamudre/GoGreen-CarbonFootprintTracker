package Database.model;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public.user")
public class User {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    protected User() {}

    public User(String username,String password) {
        this.username = username;
        this.password=password;

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
        return String.format(
                "User[username='%s', password='%s']",
                username,password);
    }
}
