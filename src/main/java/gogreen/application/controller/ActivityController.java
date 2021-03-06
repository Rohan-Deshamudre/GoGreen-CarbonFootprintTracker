package gogreen.application.controller;

import gogreen.application.client.Leaderboard;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.AddFriendRequest;
import gogreen.application.communication.AddHomeTempRequest;
import gogreen.application.communication.AddLocalProduceRequest;
import gogreen.application.communication.AddSolarPanelRequest;
import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.ChangeAchievements;
import gogreen.application.communication.FriendRequestResponse;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.Friend;
import gogreen.application.model.FriendRequest;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.FriendRepository;
import gogreen.application.repository.FriendRequestRepository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ActivityController {
    LoginController loginController = new LoginController();

    @Autowired
    private CO2Repository co2Repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger log = LogManager.getLogger(ActivityController.class.getName());

    /**
     * Handle add food requests.
     * @param req - addFoodRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid.
     *          Else returns a CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/food/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddFoodRequest req) {
        log.info(req.toString());

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducFood = CarbonUtil.getFoodCarbonReduction(
                req.getChoiceBoxValue(), req.getAmount());
        userCO2.addCO2Food(carbonReducFood);
        userCO2.addCO2Reduc(carbonReducFood);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducFood), HttpStatus.OK);
    }

    /**
     * Handle add local produce requests.
     *
     * @param req - addLocalProduceRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid.
     *          Else returns a CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/localproduce/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddLocalProduceRequest req) {
        log.info(req.toString());

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducFood = CarbonUtil
            .getLocalProduceCarbonReduction(req.getWeight(), req.isOrganic());
        userCO2.addCO2Food(carbonReducFood);
        userCO2.addCO2Reduc(carbonReducFood);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducFood), HttpStatus.OK);
    }

    /**
     * Handle add transport requests.
     *
     * @param req - addFoodRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid.
     *          Else returns a CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/transport/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleTransportAdd(@RequestBody AddTransportRequest req) {
        log.info(req.toString());

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducTransport = CarbonUtil
            .getTransportCarbonReduction(req.getTravelType(), req.getDistance());
        userCO2.addCO2Transport(carbonReducTransport);
        userCO2.addCO2Reduc(carbonReducTransport);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducTransport), HttpStatus.OK);
    }

    /**
     * Handle add home temperature requests.
     *
     * @param req - addHomeTempRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid.
     *          Else returns a CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/hometemp/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleHomeTempAdd(@RequestBody AddHomeTempRequest req) {
        log.info(req.toString());

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducHomeTemp = CarbonUtil
            .getHomeTempCarbonReduction(req.getTemperature(), req.getDuration());
        userCO2.addCO2Energy(carbonReducHomeTemp);
        userCO2.addCO2Reduc(carbonReducHomeTemp);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducHomeTemp), HttpStatus.OK);
    }

    /**
     * Handle add solar panel requests.
     *
     * @param req - add containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid.
     *          Else returns a CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/solarpanel/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleSolarPanAdd(@RequestBody AddSolarPanelRequest req) {
        log.info(req.toString());

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducSolarPanel = CarbonUtil
            .getSolarPanelCarbonReduction(req.getArea(), req.getHoursSunlight());
        userCO2.addCO2Energy(carbonReducSolarPanel);
        userCO2.addCO2Reduc(carbonReducSolarPanel);
        co2Repository.save(userCO2);
        return new ResponseEntity<>(new CO2Response(carbonReducSolarPanel), HttpStatus.OK);
    }

    /**
     * Returns a list of friends for the given login data.
     *
     * @param req the LoginRequest.
     * @return all the friends of that user.
     */
    @PostMapping(value = "/friendlist",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Leaderboard> showFriends(@RequestBody LoginData req) {

        if (!loginController.checkLoginData(req, userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Friend> all = friendRepository.findByFusername(req.getUsername());
        ArrayList<CO2> friends = new ArrayList<>();

        for (Friend friend : all) {
            List<CO2> user = co2Repository.findByCusername(friend.getFriend());
            CO2 adding = user.get(0);
            friends.add(adding);
        }

        return new ResponseEntity<>(new Leaderboard(friends), HttpStatus.OK);
    }


    /**
     * Get user stats
     *
     * @param req the LoginRequest.
     * @return The user object
     */
    @PostMapping(value = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2> userStats(@RequestBody LoginData req) {

        if (!loginController.checkLoginData(req, userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<CO2> user = co2Repository.findByCusername(req.getUsername());
        System.out.println(user);
        return new ResponseEntity<>(user.get(0), HttpStatus.OK);
    }

    /**
     * Handles changing the achievements of a user.
     *
     * @param req the ChangeAchievements.
     * @return if method was successful.
     */
    @PostMapping(value = "/changeachievements",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Boolean> changeAchievements(@RequestBody ChangeAchievements req) {
        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<CO2> user = co2Repository.findByCusername(req.getLoginData().getUsername());
        if (!user.isEmpty()) {
            CO2 update = user.get(0);
            update.setAchievement(req.getAchievements());
            co2Repository.save(update);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
    }

    /**
     * Add friend request
     *
     * @param req the LoginRequest.
     * @return is method successful.
     */
    @PostMapping(value = "/addfriend",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Boolean> addFriend(@RequestBody AddFriendRequest req) {

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Friend> check1 = friendRepository.findByFusernameAndFriend(req
                .getLoginData().getUsername(), req.getFriendUsername());

        List<FriendRequest>  check2 = friendRequestRepository
                .findByUsernameAndRequestTo(req.getLoginData()
                .getUsername(), req.getFriendUsername());

        if (check1.isEmpty() && check2.isEmpty() && !req.getLoginData()
                .getUsername().equals(req.getFriendUsername()
        )) {

            List<CO2> exist = co2Repository.findByCusername(req
                    .getFriendUsername());
            if (exist != null) {
                FriendRequest newRequest = new FriendRequest(0,
                        req.getLoginData().getUsername(),
                        req.getFriendUsername());
                friendRequestRepository.save(newRequest);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);

    }

    /**
     * Shows a list of friend requests.
     *
     * @param req the LoginRequest.
     * @return a Leaderboard with the friend requests.
     */
    @PostMapping(value = "/seefriendrequests",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Leaderboard> seeFriendRequests(@RequestBody LoginData req) {

        if (!loginController.checkLoginData(req, userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<FriendRequest> list = friendRequestRepository
            .findByRequestTo(req.getUsername());
        if (list != null) {
            ArrayList<CO2> allReq = new ArrayList<>();
            for (FriendRequest request : list) {
                List<CO2> friend = co2Repository.findByCusername(request.getUsername());
                CO2 user = friend.get(0);
                allReq.add(user);
            }
            return new ResponseEntity<>(new Leaderboard(allReq), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles the response to a friend request.
     *
     * @param req the FriendRequestResponse.
     * @return if method was successful.
     */
    @PostMapping(value = "/respondtofriendrequest",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Boolean> respondToFriendRequest(@RequestBody FriendRequestResponse req) {

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (req.isAccepted()) {
            Friend newFriend = new Friend(0, req.getLoginData().getUsername(),
                req.getFriendUsername());
            Friend newFriend1 = new Friend(0, req.getFriendUsername(),
                    req.getLoginData().getUsername());
            friendRepository.save(newFriend);
            friendRepository.save(newFriend1);

            List<FriendRequest> old = friendRequestRepository
                .findByUsernameAndRequestTo(req.getFriendUsername(),
                    req.getLoginData().getUsername());
            FriendRequest remove = old.get(0);
            friendRequestRepository.delete(remove);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        List<FriendRequest> old = friendRequestRepository
            .findByUsernameAndRequestTo(req.getFriendUsername(),
                req.getLoginData().getUsername());
        FriendRequest remove = old.get(0);
        friendRequestRepository.delete(remove);
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);

    }

    /**
     * Handles the request to remove a friend
     *
     * @param req the AddFriendRequest.
     * @return if method was successful.
     */
    @PostMapping(value = "/removefriend",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Boolean> removeFriendRequest(@RequestBody AddFriendRequest req) {

        if (!loginController.checkLoginData(req.getLoginData(), userRepository, passwordEncoder)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Friend> friend = friendRepository
                .findByFusernameAndFriend(req.getLoginData()
                .getUsername(), req.getFriendUsername());

        if (!friend.isEmpty()) {

            List<Friend> friendInverse = friendRepository
                    .findByFusernameAndFriend(req.getFriendUsername(),
                    req.getLoginData().getUsername());

            System.out.println("removing: " + friend.get(0));
            friendRepository.delete(friend.get(0));
            friendRepository.delete(friendInverse.get(0));
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
    }
}
