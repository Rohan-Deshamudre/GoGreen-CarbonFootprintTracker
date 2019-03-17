package client;

import application.User;
import ch.qos.logback.core.net.SyslogOutputStream;
import communication.LoginData;
import communication.clientMessage.LoginRequest;
import communication.serverMessage.LoginResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public class ClientApplication {

    private static final String URL = "http://localhost:8080/";

    public static void main(String args[]) throws URISyntaxException {



        boolean success = sendLoginRequest("Roy", "Donders");
        System.out.println("Success: " + success);
    }

    /**
     * get requests index page of our heroku server.
     *
     * @return the text response from the server.
     */
    public static String getRequestHeroku() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject(URL, String.class);
        return quote;
    }

    /**
     * This method sends a POST request to the server with the login information.
     *
     * @param username - the username.
     * @param password - the password.
     * @return - returns true if login successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static boolean sendLoginRequest(String username, String password)
            throws URISyntaxException {

        LoginRequest req =  new LoginRequest(new LoginData(username, password));
        RestTemplate restTemplate = new RestTemplate();

        LoginResponse res = restTemplate.postForObject(URL+"login", req, LoginResponse.class);

        System.out.println();
        System.out.println(res);

        try {
            User user = restTemplate.postForObject(URL + "user", req, User.class);
            System.out.println();
            System.out.println(user.getCO2Reduction());
            User test = new User("dumber", 420);
            System.out.println(test.getCO2Reduction());
            System.out.println(user.toString());
        } catch (HttpClientErrorException e) {
            System.out.println("UNAUTHORIZED");
        }
        return res.isSuccess();
    }

}
