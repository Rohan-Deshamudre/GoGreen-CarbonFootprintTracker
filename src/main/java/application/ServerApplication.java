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
     * Checks whether there exists a user in the database with the provided
     * username and password.
     *
     * @param username - the username of the user
     * @param password - the password of the user
     * @return a boolean for if the user has been found or not
     */
    public static boolean checkLoginData(String username, String password) {
        for (User user : repository.findByUsernameAndPassword(username, password)) {
            if (user == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Searches for the CO2 reduction in the database and returns it as
     * an integer.
     *
     * @param cusername - the username of the user
     * @return the CO2 reduction of that user
     */
    public static int getCo2Reduc(String cusername) {
        CO2 reduc = null;
        for(CO2 co2 : co2Repository.findByCusername(cusername)) {
            reduc = co2;
        }

        return reduc.getCo2reduc();
    }

    /**
     * Calls method checkLoginData(above) with the provided username and
     * password from the login request that has been made.
     *
     * @param req - the login request
     * @return a boolean from the results of checkLoginData(above)
     */
    public static boolean checkLoginData(LoginRequest req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }

}
