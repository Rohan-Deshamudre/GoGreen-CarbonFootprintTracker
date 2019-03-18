package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("repository")

public class ServerApplication {

    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);


    }




}
