package gogreen.application.controller;

import gogreen.application.client.ClientApplication;
import gogreen.application.client.Leaderboard;
import gogreen.application.communication.*;
import gogreen.application.model.*;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.FriendRepository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ActivityController {

    @Autowired
    private CO2Repository co2Repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    /**
     * SpringBoot automatically wires the CO2Repository instance.
     * into this class using this setter method
     * @param co2Repository - the CO@Repository instance
     */


    private Logger log = LogManager.getLogger(ActivityController.class.getName());

    /**
     * Add the data of co2.
     * @param request Co2 request
     * @return returns the method
     */
    public CO2Response addFoodData(AddFoodRequest request) {

        CO2Response response = new CO2Response();
        String user = request.getLoginData().getUsername();

        //Validate whether the input user exists in the user table
        List<User> userList = userRepository.findByUsername(user);
        if (userList == null || userList.isEmpty()) {
            //This condition gets executed if user is not available in the system.
            //Hence the carbon footprint cannot be added.
            response.setResult(false);
            return response;
        }
        //Calculate the pre-defined carbon footprint
        int currentCarbonfootprint = CarbonUtil.getFoodCarbonfootprint(request.getChoiceBoxValue());

        List<CO2> dbUserList = co2Repository.findByCusername(user);
        if (dbUserList != null && !dbUserList.isEmpty()) {
            //This condition gets executed if a user is already having a carbon footprint
            CO2 dbUser = dbUserList.get(0);
            int oldCarbonfootprint = dbUser.getCo2reduc();
            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
            dbUser.setCo2reduc(newCarbonfootprint);
            co2Repository.save(dbUser);
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        } else {
            //This condition gets executed if user is creating their first carbon footprint
            int oldCarbonfootprint = 0;
            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
            co2Repository.save(new CO2(user, 0, 0, 0, newCarbonfootprint));
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        }

        return response;
    }

    /**
     * Handle the addition of carbon footprint.
     * @param req requests the co2
     * @return returns te method
     */

    @RequestMapping(value = "/food/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddFoodRequest req) {
        log.info(req.toString());
        CO2Response res = addFoodData(req);
        return new ResponseEntity<CO2Response>(res, HttpStatus.OK);
    }


    /**
     * Handles requests to add transport data to the users co2 score.
     * @param req - addTransportRequest object containing the data to add.
     * @return response containing the updates done to the server.
     */
    @RequestMapping(value = "/transport/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleTransportAdd(@RequestBody AddTransportRequest req) {
        log.info(req.toString());
        CO2Response res = addTransportData(req);
        return new ResponseEntity<CO2Response>(res, HttpStatus.OK);
    }

    /**
     * Description for the method.
     * @param request the request
     * @return CO2 response
     */
    public CO2Response addTransportData(AddTransportRequest request) {
        CO2Response response = new CO2Response();
        String user = request.getLoginData().getUsername();
        
        //Validate whether the input user exists in the user table
        List<User> userList = userRepository.findByUsername(user);
        if (userList == null || userList.isEmpty()) {
            //This condition gets executed if user is not available in the system.
            // Hence the carbon footprint cannot be added.
            response.setResult(false);
            return response;
        }
        int currentCarbonfootprint = CarbonUtil
                .getTransportCarbonfootprint(request.getDistance(),request.getTimesaweek());

        List<CO2> dbUserList = co2Repository.findByCusername(user);
        if (dbUserList != null && !dbUserList.isEmpty()) {
            //This condition gets executed if a user is already having a carbon footprint
            CO2 dbUser = dbUserList.get(0);
            int oldCarbonfootprint = dbUser.getCo2food();
            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
            dbUser.setCo2food(newCarbonfootprint);
            co2Repository.save(dbUser);
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        } else {
            //This condition gets executed if user is creating their first carbon footprint
            int oldCarbonfootprint = 0;
            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
            co2Repository.save(new CO2(user, newCarbonfootprint, 0, 0, 0));
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        }
        return response;
    }


    /**
     * Find all the carbon.
     * @return returns the carbon
     */

    @RequestMapping("/carbon/findAll")
    @ResponseBody
    public String carbonFindAll() {
        String result = "";

        for (CO2 user : co2Repository.findAll()) {
            result += user.toString() + "<br>";
        }

        return result;
    }


    /**
     * Makes a list of friends.
     * @param req the LoginRequest.
     * @return all the friends of that user.
     */
    @RequestMapping(value = "/friendlist",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Leaderboard showFriends(@RequestBody LoginRequest req) {

        // TODO: check login data.

        boolean result = true;


        if (result) {
            String username = req.getLoginData().getUsername();
            List<Friend> all = friendRepository.findByFusername(username);
            ArrayList<CO2> friends = new ArrayList<CO2>();

//            for (Friend friend : all) {
//                List<CO2> user = co2Repository.findByCusername(friend.getFriend());
//                friends.add(user.get(0));
//            }

           Leaderboard leaderboard = new Leaderboard();

            return leaderboard;
        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Makes a list of friends.
     * @param req the LoginRequest.
     * @return all the friends of that user.
     */
    @RequestMapping(value = "/user",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CO2 userStats(@RequestBody LoginRequest req) {

        // TODO: check login data.

        boolean result = true;


        if (result) {
            CO2 user = new CO2("TestUser123", 1, 2, 3, 6);
            return user;

        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Makes a list of friends.
     * @param req the LoginRequest.
     * @return is method successful.
     */
    @RequestMapping(value = "/addfriend",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public boolean addFriend(@RequestBody AddFriendRequest req) {

        // TODO: check login data.

        boolean result = true;

        if (result) {
            System.out.println("Friend USERNAME: " + req.getFriendUsername());
            boolean success = true;
            return true;

        } else {
            throw new IllegalArgumentException();
        }
    }

}
