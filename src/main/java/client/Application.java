package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


public class Application {

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("https://fast-meadow-28971.herokuapp.com/", String.class);
        System.out.println(quote);
    }

}