package client;

import communication.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public class ClientApplication {

    private static final String URL = "https://gogreen32.herokuapp.com/";
//    private static final String URL = "http://localhost:8080/";

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
        LoginRequest req = new LoginRequest(new LoginData(username, password));

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "login";
        LoginResponse res = restTemplate.postForObject(loginUrl, req, LoginResponse.class);

        System.out.println();
        System.out.println(res);

        return res.isSuccess();
    }

    public static String co2Add(String choiceBoxValue, String username)
            throws URISyntaxException {
        String resMessage = "";
        Co2Request req = new Co2Request(choiceBoxValue,username);

        RestTemplate restTemplate = new RestTemplate();

        String co2AddURL = URL + "carbon/add";
        Co2Response res = restTemplate.postForObject(co2AddURL, req, Co2Response.class);
        System.out.println();
        System.out.println(res);
        if(res!=null && res.getResult()){
            resMessage="Congratulations "+res.getUsername()+"! Your Carbon Footprint is updated from "+res.getOldCarbonfootprint()
                    + " to " + res.getNewCarbonfootprint();
        }
        else{
            resMessage="We are extremely sorry! There seems to be an issue in updating your Carbon Footprint. Are you using the correct username?";
        }
        return resMessage;
    }

}
