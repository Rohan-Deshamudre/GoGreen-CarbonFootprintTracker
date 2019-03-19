package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ServerApplication {

    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

    /**
     * Runs the SpringApplication.
     * @param args main method
     */

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);


    }




}
