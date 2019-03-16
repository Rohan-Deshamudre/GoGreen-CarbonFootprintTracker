package application;

import application.communication.LoginRequest;
import application.model.CO2;
import application.model.User;
import application.repository.UserRepository;
import application.repository.CO2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ServerApplication {

    private static UserRepository repository;
    private static CO2Repository co2Repository;

    /**
     * SpringBoot automatically wires the UserRepository instance
     * into this class using this setter method
     * @param repository - the UserRepository instance
     */
    @Autowired
    private void setRepository(UserRepository repository) {
        ServerApplication.repository = repository;
    }

    /**
     * SpringBoot automatically wires the CO2Repository instance
     * into this class using this setter method
     * @param co2repository - the CO2Repository instance
     */
    @Autowired
    private void setCo2Repository(CO2Repository co2repository) { ServerApplication.co2Repository = co2repository; }

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
    public static int getCo2Reduc(String cusername) {
        CO2 reduc = null;
        for(CO2 co2 : co2Repository.findByCusername(cusername)) {
            reduc = co2;
        }

        return reduc.getCo2reduc();
    }

    public static boolean checkLoginData(LoginRequest req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }

}
