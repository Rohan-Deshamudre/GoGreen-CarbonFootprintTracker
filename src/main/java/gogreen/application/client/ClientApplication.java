package gogreen.application.client;

import gogreen.application.communication.*;
import gogreen.application.model.CO2;
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
     * This method sends a POST request to the server with the login information.
     * Request friend list.
     *
     * @return - returns true if method was successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static Leaderboard sendGetFriendListRequest()
            throws URISyntaxException {

        LoginRequest req = new LoginRequest(loginData);
        System.out.println(req);

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "friendlist";
        Leaderboard res = restTemplate.postForObject(loginUrl, req, Leaderboard.class);

        System.out.println(res);

        return res;
    }

    /**
     * This method sends a POST request to the server with the login information.
     * Request friend list.
     *
     * @return - returns true if method was successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static CO2 sendGetUserStatsRequest()
            throws URISyntaxException {

        LoginRequest req = new LoginRequest(loginData);

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "user";
        CO2 res = restTemplate.postForObject(loginUrl, req, CO2.class);

        System.out.println(res);

        return res;
    }

    /**
     * This method sends a POST request to the server with the login information.
     * Request adding a friend.
     *
     * @return - returns true if method was successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static boolean sendAddFriendRequest(String username)
            throws URISyntaxException {

        AddFriendRequest req = new AddFriendRequest(loginData, username);

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "addfriend";
        boolean res = restTemplate.postForObject(loginUrl, req, boolean.class);

        System.out.println(res);

        return res;
    }

    /**
     * Requests all your friend requests.
     *
     * @return Leaderboard with the users that sent you friend requests.
     * @throws URISyntaxException - can throw exception.
     */
    public static Leaderboard getFriendRequests()
            throws URISyntaxException {

        LoginRequest req = new LoginRequest(loginData);

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "seefriendrequests";
        Leaderboard res = restTemplate.postForObject(loginUrl, req, Leaderboard.class);

        System.out.println(res);

        return res;
    }

    /**
     * This method sends a POST request to the server with the login information.
     * Request adding a friend.
     *
     * @return - returns true if method was successful.
     * @throws URISyntaxException - can throw exception.
     */
    public static boolean respondToFriendRequest(String username, boolean success)
            throws URISyntaxException {

        FriendRequestResponse req = new FriendRequestResponse(loginData, username, success);

        RestTemplate restTemplate = new RestTemplate();

        String loginUrl = URL + "respondtofriendrequest";
        boolean res = restTemplate.postForObject(loginUrl, req, boolean.class);

        System.out.println(res);

        return res;
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

    /**
     * Send a request to add values to the database.
     * @param distance the distance using transportation
     * @param timesaweek the amount this has been done
     * @return returns a string
     * @throws URISyntaxException throws an exception
     */
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

        AddHomeTempRequest req = new AddHomeTempRequest(loginData,temperature,duration);
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


    public static void clearLoginData() {
        loginData = null;
    }
}