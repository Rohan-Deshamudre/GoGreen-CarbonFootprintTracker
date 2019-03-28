package gogreen.application.client;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.LoginData;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ClientApplication {

    //private static final String URL = "https://gogreen32.herokuapp.com/";
    private static final String URL = "http://localhost:8080/";

    private static Logger log = LogManager.getLogger(ClientApplication.class.getName());
    private static RestTemplate restTemplate = new RestTemplate();

    private static LoginData loginData = null;

    /**
     * get requests index page of our heroku server.
     *
     * @return the text response from the server.
     */
    public static String getRequestHeroku() {
        String quote = restTemplate.getForObject(URL, String.class);
        return quote;
    }

    /**
     * This method sends a POST request to the server with the login information.
     *
     * @param username - the username.
     * @param password - the password.
     * @returns - true iff login is successful.
     */
    public static boolean sendLoginRequest(String username, String password) {
        LoginData curLoginData = new LoginData(username, password);

        try {
            log.info("Logging in to " + username);
            restTemplate
                .postForLocation(URL + "login", curLoginData);
        } catch (RestClientException e) {
            // server returned a http error code
            return false;
        }

        log.info("Login successful!");
        loginData = curLoginData;
        return true;
    }

    /**
     * Clears the currently stored login data.
     */
    public static void clearLoginData() {
        loginData = null;
    }

    /**
     * This method sends a POST request to the server with login credentials for the creation of a
     * new account.
     *
     * @param username - the username.
     * @param password - the password.
     * @return - true iff the registration was successful.
     */
    public static boolean sendRegisterRequest(String username, String password) {
        LoginData curLoginData = new LoginData(username, password);

        log.info("Attempting to register a new account for " + username);
        try {
            restTemplate
                .postForLocation(URL + "login/register", curLoginData);
        } catch (RestClientException e) {
            // registration unsuccessful.
            return false;
        }

        log.info("Account registration successful!");
        loginData = curLoginData;
        return true;
    }

    /**
     * This method sends a post request to the server with data provided by the user about their
     * diet.
     *
     * @param choiceBoxValue - decide the value that will be added to the user.
     * @param amount - the amount of food added.
     * @return CO2Response - response object containing data returned by server.
     * @throws RestClientException - on request unsuccessful.
     */
    public static CO2Response sendAddFoodRequest(String choiceBoxValue, int amount)
        throws RestClientException {
        AddFoodRequest req = new AddFoodRequest(loginData, choiceBoxValue, amount);

        log.info("Sending add food request for: " + loginData.getUsername());
        ResponseEntity<CO2Response> res = restTemplate
            .postForEntity(URL + "activity/food/add", req, CO2Response.class);
        log.info(res);

        return res.getBody();
    }

    /**
     * Send a request to add values to the database.
     *
     * @param distance the distance using transportation
     * @param timesaweek the amount this has been done
     * @return returns a string
     */
    public static String sendAddTransportRequest(int distance, int timesaweek) {
        String resMessage = "";
//        if (loginData == null) {
//            return "We are extremely sorry! "
//                + "There seems to be an issue in updating your Carbon Footprint."
//                + "Try logging out and in again.";
//        }
//        AddTransportRequest req = new AddTransportRequest(loginData, distance, timesaweek);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String co2AddUrl = URL + "transport/add";
//        CO2Response res = restTemplate.postForObject(co2AddUrl, req, CO2Response.class);
//        System.out.println();
//        System.out.println(res);
//        if (res != null && res.getResult()) {
//            resMessage = "Congratulations" + loginData.getUsername()
//                + "! Your Carbon Footprint is updated from"
//                + res.getOldCarbonfootprint()
//                + " to " + res.getNewCarbonfootprint();
//        } else {
//            resMessage = "We are extremely sorry! "
//                + "There seems to be an issue in updating your Carbon Footprint."
//                + "Are you using the correct username?";
//        }
        return resMessage;
    }

    /**
     * Description for this method.
     *
     * @param temperature the temperature
     * @param duration the duration
     * @return a string
     * @throws URISyntaxException throws an exception
     */
    public static String sendAddHomeTempRequest(int temperature, int duration) {
        String resMessage = "";
//        if (loginData == null) {
//            return "We are extremely sorry! "
//                + "There seems to be an issue in updating your Carbon Footprint."
//                + "Try logging out and in again.";
//        }
//
//        AddHomeTempRequest req = new AddHomeTempRequest(loginData, temperature, duration);
//        RestTemplate restTemplate = new RestTemplate();
//
//        String co2Addurl = URL + "homeTemp/add";
//
//        CO2Response res = restTemplate.postForObject(co2Addurl, req, CO2Response.class);
//        System.out.println();
//        System.out.println(res);
//        if (res != null && res.getResult()) {
//            resMessage = "Congratulations " + loginData.getUsername()
//                + "! Your Carbon Footprint is updated from "
//                + res.getOldCarbonfootprint()
//                + " to " + res.getNewCarbonfootprint();
//        } else {
//            resMessage = "We are extremely sorry!"
//                + "There seems to be an issue in updating your Carbon Footprint."
//                + " Are you using the correct username?";
//        }
        return resMessage;
    }
}