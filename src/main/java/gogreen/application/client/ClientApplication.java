package gogreen.application.client;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.AddFriendRequest;
import gogreen.application.communication.AddHomeTempRequest;
import gogreen.application.communication.AddLocalProduceRequest;
import gogreen.application.communication.AddSolarPanelRequest;
import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.AddTransportRequest.TravelType;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.ClientMessage;
import gogreen.application.communication.FriendRequestResponse;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

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
     * This method sends a GET request to the server with the login information. Request friend
     * list.
     *
     * @return - returns true if method was successful.
     */
    public static Leaderboard sendGetFriendListRequest() throws RestClientException {
        ResponseEntity<Leaderboard> res = restTemplate
            .postForEntity(URL + "friendlist", loginData, Leaderboard.class);

        System.out.println(res);
        return res.getBody();
    }

    /**
     * This method sends a GET request to the server with the login information. Request friend
     * list.
     *
     * @return - returns true if method was successful.
     */
    public static CO2 sendGetUserStatsRequest()
        throws RestClientException {
        ResponseEntity<CO2> res = restTemplate.postForEntity(URL + "user", loginData, CO2.class);

        System.out.println(res);
        return res.getBody();
    }

    /**
     * This method sends a POST request to the server with the login information. Request adding a
     * friend.
     *
     * @return - returns true if method was successful.
     */
    public static boolean sendAddFriendRequest(String username)
        throws RestClientException {
        AddFriendRequest req = new AddFriendRequest(loginData, username);

        ResponseEntity<Boolean> res = restTemplate.postForEntity(URL + "addfriend",
                req, Boolean.class);
        System.out.println(res);

        return res.getBody();
    }

    /**
     * Requests all your friend requests.
     *
     * @return Leaderboard with the users that sent you friend requests.
     * @throws URISyntaxException - can throw exception.
     */
    public static Leaderboard getFriendRequests()
        throws RestClientException {

        ResponseEntity<Leaderboard> res = restTemplate.postForEntity(URL + "seefriendrequests",
                loginData, Leaderboard.class);

        System.out.println(res);

        return res.getBody();
    }

    /**
     * This method sends a POST request to the server with the login information. Request adding a
     * friend.
     *
     * @return - returns true if method was successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static boolean respondToFriendRequest(String username, boolean success)
        throws RestClientException {

        FriendRequestResponse req = new FriendRequestResponse(loginData, username, success);

        ResponseEntity<Boolean> res = restTemplate.postForEntity(URL + "respondtofriendrequest",
                req, Boolean.class);

        System.out.println(res);

        return res.getBody();
    }


    /**
     * Generic implementation of sending a post request containing activity add data to the server
     *
     * @param urlPath - path leading to correct api function.
     * @param requestData - body of the request.
     * @return - CO2Response describing the change in CO2 made.
     * @throws RestClientException - iff the status code received is not positive.
     */
    private static <T extends ClientMessage> CO2Response sendActivityAddRequest(String urlPath,
        T requestData) throws RestClientException {
        log.info("sending activity add request for: " + urlPath);
        ResponseEntity<CO2Response> res = restTemplate
            .postForEntity(URL + urlPath, requestData, CO2Response.class);
        log.info(res);

        return res.getBody();
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
        return sendActivityAddRequest("activity/food/add", req);
    }


    /**
     * This method sends a post request to the server with data provided by the user about the local
     * produce they bought.
     *
     * @param weight - the weight of the local produce bought.
     * @param organic - true iff the produce is organic produce.
     * @return CO2Response - response object containing data returned by server.
     * @throws RestClientException - on request unsuccessful.
     */
    public static CO2Response sendAddLocalProduceRequest(int weight, boolean organic)
        throws RestClientException {
        AddLocalProduceRequest req = new AddLocalProduceRequest(loginData, weight, organic);
        return sendActivityAddRequest("activity/localproduce/add", req);
    }

    /**
     * This method sends a post request to the server with data provided by the user about their
     * transportation habits.
     *
     * @param distance - average distance travelled.
     * @param travelType - the type of travel.
     * @return CO2Response - response object containing data returned by server.
     * @throws RestClientException - on request unsuccessful.
     */
    public static CO2Response sendAddTransportRequest(TravelType travelType, int distance)
        throws RestClientException {
        AddTransportRequest req = new AddTransportRequest(loginData, travelType, distance);
        return sendActivityAddRequest("activity/transport/add", req);
    }

    /**
     * This method sends a post request to the server with data provided by the user about their
     * transportation habits.
     *
     * @param temperature - the temperature the thermostat is set to.
     * @param duration - the duration this temperature is set.
     * @return - response object containing data returned by server.
     * @throws RestClientException - on request unsuccessful.
     */
    public static CO2Response sendAddHomeTempRequest(int temperature, int duration)
        throws RestClientException {
        AddHomeTempRequest req = new AddHomeTempRequest(loginData, temperature, duration);
        return sendActivityAddRequest("activity/hometemp/add", req);
    }

    /**
     * This method sends a post request to the server with data provided by the user about their
     * solar panels.
     *
     * @param area - the m2 of solar panel added.
     * @param hoursSunlight - hours of sunlight these solar panels received.
     * @return - response object containing data returned by server.
     * @throws RestClientException - on request unsuccessful.
     */
    public static CO2Response sendAddSolarPanelRequest(int area, int hoursSunlight)
        throws RestClientException {
        AddSolarPanelRequest req = new AddSolarPanelRequest(loginData, area, hoursSunlight);
        return sendActivityAddRequest("activity/solarpanel/add", req);
    }
}