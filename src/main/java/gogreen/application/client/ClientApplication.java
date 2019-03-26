package gogreen.application.client;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.AddHomeTempRequest;
import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.LoginData;
import gogreen.application.communication.LoginRequest;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

public class ClientApplication {

    //private static final String URL = "https://gogreen32.herokuapp.com/";
    private static final String URL = "http://localhost:8080/";

    private static Logger log = LogManager.getLogger(ClientApplication.class.getName());

    private static LoginData loginData = null;

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
     * @return - returns true iff login successful.
     */
    public static boolean sendLoginRequest(String username, String password) {

        LoginData curLoginData = new LoginData(username, password);

        log.info("Logging in...");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity res = restTemplate
            .postForEntity(URL + "login", new LoginRequest(curLoginData), ResponseBody.class);

        if (res.getStatusCode().equals(HttpStatus.OK)) {
            log.info("Login successful!");
            loginData = curLoginData;
            return true;
        }

        return false;
    }

    public static void clearLoginData() {
        loginData = null;
    }

    /**
     * Adding co2 to the user.
     *
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

    /**
     * Send a request to add values to the database.
     *
     * @param distance the distance using transportation
     * @param timesaweek the amount this has been done
     * @return returns a string
     * @throws URISyntaxException throws an exception
     */

    public static String sendAddTransportRequest(int distance, int timesaweek)
        throws URISyntaxException {
        String resMessage;
        if (loginData == null) {
            return "We are extremely sorry! "
                + "There seems to be an issue in updating your Carbon Footprint."
                + "Try logging out and in again.";
        }
        AddTransportRequest req = new AddTransportRequest(loginData, distance, timesaweek);

        RestTemplate restTemplate = new RestTemplate();

        String co2AddUrl = URL + "transport/add";
        CO2Response res = restTemplate.postForObject(co2AddUrl, req, CO2Response.class);
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

    /**
     * Description for this method.
     *
     * @param temperature the temperature
     * @param duration the duration
     * @return a string
     * @throws URISyntaxException throws an exception
     */
    public static String sendAddHomeTempRequest(int temperature, int duration)
        throws URISyntaxException {
        String resMessage = "";
        if (loginData == null) {
            return "We are extremely sorry! "
                + "There seems to be an issue in updating your Carbon Footprint."
                + "Try logging out and in again.";
        }

        AddHomeTempRequest req = new AddHomeTempRequest(loginData, temperature, duration);
        RestTemplate restTemplate = new RestTemplate();

        String co2Addurl = URL + "homeTemp/add";

        CO2Response res = restTemplate.postForObject(co2Addurl, req, CO2Response.class);
        System.out.println();
        System.out.println(res);
        if (res != null && res.getResult()) {
            resMessage = "Congratulations " + loginData.getUsername()
                + "! Your Carbon Footprint is updated from "
                + res.getOldCarbonfootprint()
                + " to " + res.getNewCarbonfootprint();
        } else {
            resMessage = "We are extremely sorry!"
                + "There seems to be an issue in updating your Carbon Footprint."
                + " Are you using the correct username?";
        }
        return resMessage;
    }
}