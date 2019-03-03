package client;

import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;


public class ClientApplication {

    private static final String URL = "http://localhost:8080/";

    /**
     * PLACEHOLDER JAVADOC.
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String args[]) throws URISyntaxException {
        //getRequestHeroku();
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
     * @param username
     * @param password
     * @return
     * @throws URISyntaxException
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
