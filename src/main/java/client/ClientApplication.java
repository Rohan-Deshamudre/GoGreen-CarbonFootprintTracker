package client;


import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;


public class ClientApplication {

    private final static String URL = "https://gogreen32.herokuapp.com/";

    public static void main(String args[]) throws URISyntaxException {
        boolean success = sendLoginRequest("Roy", "Donders");
        System.out.println("Success: " + success);
    }

    /**
     * get requests index page of our heroku server
     *
     * @return the text response from the server
     */
    public static String getRequestHeroku() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject(URL, String.class);
        return quote;
    }

    /**
     * This method sends a POST request to the server with the login information
     * @param username
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static boolean sendLoginRequest(String username, String password) throws URISyntaxException {
        LoginRequest req = new LoginRequest(username, password);

        RestTemplate restTemplate = new RestTemplate();

        LoginResponse res = restTemplate.postForObject(URL + "login", req, LoginResponse.class);

        System.out.println();
        System.out.println(res);

        return res.getSuccess();
    }

}
