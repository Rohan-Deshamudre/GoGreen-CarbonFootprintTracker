package client;

import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


public class ClientApplication {

    private final static String URL = "http://localhost:8080/";

    public static void main(String args[]) throws URISyntaxException {
//        getRequestHeroku();
        boolean success = sendLoginRequest("Roy", "Donders");
        System.out.println("Success: " + success);
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

    public static boolean sendLoginRequest(String username, String password) throws URISyntaxException {
        LoginRequest req = new LoginRequest(username, password);

        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(URL + "login");

        LoginResponse res = restTemplate.postForObject(uri, req, LoginResponse.class);

        System.out.println();
        System.out.println(res);

        return res.getSuccess();
    }

}
