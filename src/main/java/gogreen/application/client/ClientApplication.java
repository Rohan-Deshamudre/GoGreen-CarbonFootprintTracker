package gogreen.application.client;

import gogreen.application.communication.*;
import gogreen.application.communication.CO2Response;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public class ClientApplication {

    //private static final String URL = "https://gogreen32.herokuapp.com/";
    private static final String URL = "http://localhost:8080/";

    private static LoginData loginData = null;

    public static void main(String args[]) throws URISyntaxException {
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

        if (res.isSuccess()) {
            loginData = new LoginData(username, password);
        }
        return res.isSuccess();
    }

    /**
     * Adding co2 to the user.
     * @param choiceBoxValue decide the value that will be added to the user
     * @param amount the amount of food added
     * @return returns the message
     * @throws URISyntaxException gives syntax exception
     */

    public static String sendAddFoodRequest(String choiceBoxValue, int amount)
            throws URISyntaxException {
        String resMessage;
        if (loginData == null) {
            return "We are extremely sorry! "
                    + "There seems to be an issue in updating your Carbon Footprint."
                    + "Try logging out and in again.";
        }

        AddFoodRequest req = new AddFoodRequest(loginData, choiceBoxValue, amount);

        RestTemplate restTemplate = new RestTemplate();

        String co2Addurl = URL + "food/add";
        CO2Response res = restTemplate.postForObject(co2Addurl, req, CO2Response.class);
        System.out.println();
        System.out.println(res);
        if (res != null && res.getResult()) {
            resMessage = "Congratulations" + loginData.getUsername()
                    + "! Your Carbon Footprint is updated from"
                    + res.getOldCarbonfootprint()
                    + " to " + res.getNewCarbonfootprint();
        } else {
            resMessage = "We are extremely sorry! "
                    + "There seems to be an issue in updating your Carbon Footprint."
                    + "Are you using the correct username?";
        }

        return resMessage;
    }

    public static String sendAddTransportRequest(int distance, int timesaweek)
            throws URISyntaxException  {
        String resMessage;
        if (loginData == null) {
            return "We are extremely sorry! "
                    + "There seems to be an issue in updating your Carbon Footprint."
                    + "Try logging out and in again.";
        }
        AddTransportRequest req = new AddTransportRequest(loginData,distance,timesaweek);

        RestTemplate restTemplate = new RestTemplate();

        String co2AddURL = URL + "transport/add";
        CO2Response res = restTemplate.postForObject(co2AddURL, req, CO2Response.class);
        System.out.println();
        System.out.println(res);
        if (res != null && res.getResult()) {
            resMessage = "Congratulations" + loginData.getUsername()
                    + "! Your Carbon Footprint is updated from"
                    + res.getOldCarbonfootprint()
                    + " to " + res.getNewCarbonfootprint();
        } else {
            resMessage = "We are extremely sorry! "
                    + "There seems to be an issue in updating your Carbon Footprint."
                    + "Are you using the correct username?";
        }
        return resMessage;
    }

    public static String sendAddHomeTempRequest(int temperature, int duration)
            throws URISyntaxException{
        String resMessage = "";
        if (loginData == null) {
            return "We are extremely sorry! "
                    + "There seems to be an issue in updating your Carbon Footprint."
                    + "Try logging out and in again.";
        }

        AddHomeTempRequest req = new AddHomeTempRequest(loginData,temperature,duration);
        RestTemplate restTemplate = new RestTemplate();

        String co2AddURL = URL + "homeTemp/add";

        CO2Response res = restTemplate.postForObject(co2AddURL, req, CO2Response.class);
        System.out.println();
        System.out.println(res);
        if(res!=null && res.getResult()){
            resMessage="Congratulations "+loginData.getUsername()+"! Your Carbon Footprint is updated from "+res.getOldCarbonfootprint()
                    + " to " + res.getNewCarbonfootprint();
        }
        else{
            resMessage="We are extremely sorry! There seems to be an issue in updating your Carbon Footprint. Are you using the correct username?";
        }
        return resMessage;
    }


    public static void clearLoginData() {
        loginData = null;
    }
}