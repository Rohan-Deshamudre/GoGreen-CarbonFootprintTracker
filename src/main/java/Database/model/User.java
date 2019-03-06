package Database.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Column(name = "userame")
    private String username;

    @Column(name = "salt")
    private String salt;

    @Column(name = "hashed_password")
    private String hashed_password;

    protected User() {}

    public User(String username, String salt, String hashed_password) {
        this.username = username;
        this.salt = salt;
        this.hashed_password=hashed_password;

    }

    @Override
    public String toString() {
        return String.format(
                "User[username='%s', salt='%s', hashed_password='%s']",
                username,salt,hashed_password);
    }
}
