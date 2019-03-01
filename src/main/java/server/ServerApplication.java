package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * THIS IS TEMPORARY
     * In the future this will be connected to the database.
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLoginData(String username, String password) {
        if (username.equals("John") && password.equals("Cena")) {
            return true;
        }
        return false;
    }

}
