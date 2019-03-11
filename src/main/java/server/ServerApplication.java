package server;

import application.User;
import communication.LoginData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        User dummy = new User("Dummy", 420);
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * THIS IS TEMPORARY
     * In the future this will be connected to the database in the logincontroler method
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLoginData(String username, String password) {
        if (username.equals("dummy") && password.equals("123")) {
            return true;
        }
        return false;
    }

    public static boolean checkLoginData(LoginData req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }

}
