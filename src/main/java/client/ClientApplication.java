package client;

import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;


public class ClientApplication {

    private static final String URL = "https://gogreen32.herokuapp.com/";

    /**
     * PLACEHOLDER JAVADOC.
     * @param args - java run arguments, not used
     * @throws URISyntaxException - exception thrown when sendLoginRequest uses the wrong url
     */
    public static void main(String args[]) throws URISyntaxException {
        boolean success = sendLoginRequest("Roy", "Donders");
        System.out.println("Success: " + success);
    }

    /**
     * get requests index page of our heroku server.
     * @return the text response from the server
     */
    public static String getRequestHeroku() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject(URL, String.class);
        return quote;
    }

    /**
     * PLACEHOLDER JAVADOC.
     * @param username - String containing the username
     * @param password - String containing the password
     * @return - true iff the user/password combination resulted in a successful login
     *@throws URISyntaxException exception thrown when the wrong url is used
     */
    public static boolean sendLoginRequest(String username, String password)
            throws URISyntaxException {
        LoginRequest req = new LoginRequest(username, password);

        RestTemplate restTemplate = new RestTemplate();

        LoginResponse res = restTemplate.postForObject(URL + "login", req, LoginResponse.class);

        System.out.println();
        System.out.println(res);

        return res.getSuccess();
    }

}
