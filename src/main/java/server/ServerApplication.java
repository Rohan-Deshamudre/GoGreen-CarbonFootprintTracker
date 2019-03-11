package server;

import communication.LoginRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"Database"})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * THIS IS TEMPORARY
     * In the future this will be connected to the database.
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLoginData(String username, String password) {
        if (username.equals("John") && password.equals("Wick")) {
            return true;
        }
        return false;
    }

    public static boolean checkLoginData(LoginRequest req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }

}
