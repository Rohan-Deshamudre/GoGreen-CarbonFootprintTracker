package application;

import application.communication.LoginRequest;
import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ServerApplication {

    @Autowired
    private static UserRepository repository;

   // @Bean
   // public PasswordEncoder passwordEncoder(){
   //     return new BCryptPasswordEncoder();
   // }



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

        for (User user : repository.findByUsernameAndPassword(username, password)) {
            if (user == null) {
                return false;
            }
        }
        return true;
    }
//        if (username.equals("John")&& password.equals("Wick")) {
//            return true;
//        }
//        return false;


    public static boolean checkLoginData(LoginRequest req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }

}
