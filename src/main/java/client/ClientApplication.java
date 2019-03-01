package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


public class ClientApplication {

    private final static String URL = "https://fast-meadow-28971.herokuapp.com/";

    public static void main(String args[]) {
        getRequestHeroku();
    }

    /**
     * get requests index page of our heroku server
     * @return the text response from the server
     */
    public static String getRequestHeroku () {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject(URL, String.class);
        return quote;
    }

}
