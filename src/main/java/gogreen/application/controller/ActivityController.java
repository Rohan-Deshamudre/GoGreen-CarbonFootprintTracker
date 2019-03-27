package gogreen.application.controller;

import static gogreen.application.controller.LoginController.checkLoginData;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.ErrorMessage;
import gogreen.application.model.CO2;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ActivityController {

    @Autowired
    private CO2Repository co2Repository;

    @Autowired
    private UserRepository userRepository;

    private Logger log = LogManager.getLogger(ActivityController.class.getName());

    /**
     * Handle add food requests.
     *
     * @param req - addFoodRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' when the credentials supplied in the request are
     * invalid. Else returns a CO2Response containing the changes made to the user's CO2 records.
     */
    @PostMapping(value = "/activity/food/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddFoodRequest req) {
        log.info(req.toString());

        // validate session
        try {
            checkLoginData(req.getLoginData(), userRepository);
        } catch (ErrorMessage errorMessage) {
            CO2Response res = new CO2Response();
            res.setErrorMessage(errorMessage);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducFood = CarbonUtil.getFoodCarbonfootprint(req.getChoiceBoxValue());
        userCO2.addCO2Food(carbonReducFood);
        userCO2.addCO2Reduc(carbonReducFood);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducFood), HttpStatus.OK);
    }

//    /**
//     * Handles requests to add transport data to the users co2 score.
//     *
//     * @param req - addTransportRequest object containing the data to add.
//     * @return response containing the updates done to the server.
//     */
//    @RequestMapping(value = "/transport/add",
//        consumes = {MediaType.APPLICATION_JSON_VALUE},
//        produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public ResponseEntity<CO2Response> handleTransportAdd(@RequestBody AddTransportRequest req) {
//        log.info(req.toString());
//        CO2Response res = addTransportData(req);
//        return new ResponseEntity<CO2Response>(res, HttpStatus.OK);
//    }

//    /**
//     * Description for the method.
//     *
//     * @param request the request
//     * @return CO2 response
//     */
//    public CO2Response addTransportData(AddTransportRequest request) {
//        CO2Response response = new CO2Response();
//        String user = request.getLoginData().getUsername();
//
//        //Validate whether the input user exists in the user table
//        List<User> userList = userRepository.findByUsername(user);
//        if (userList == null || userList.isEmpty()) {
//            //This condition gets executed if user is not available in the system.
//            // Hence the carbon footprint cannot be added.
//            response.setResult(false);
//            return response;
//        }
//        int currentCarbonfootprint = CarbonUtil
//            .getTransportCarbonfootprint(request.getDistance(), request.getTimesaweek());
//
//        List<CO2> dbUserList = co2Repository.findByCusername(user);
//        if (dbUserList != null && !dbUserList.isEmpty()) {
//            //This condition gets executed if a user is already having a carbon footprint
//            CO2 dbUser = dbUserList.get(0);
//            int oldCarbonfootprint = dbUser.getCo2food();
//            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
//            dbUser.setCo2food(newCarbonfootprint);
//            co2Repository.save(dbUser);
//            response.setNewCarbonfootprint(newCarbonfootprint);
//            response.setOldCarbonfootprint(oldCarbonfootprint);
//            response.setResult(true);
//        } else {
//            //This condition gets executed if user is creating their first carbon footprint
//            int oldCarbonfootprint = 0;
//            int newCarbonfootprint = oldCarbonfootprint + currentCarbonfootprint;
//            co2Repository.save(new CO2(user, newCarbonfootprint, 0, 0, 0));
//            response.setNewCarbonfootprint(newCarbonfootprint);
//            response.setOldCarbonfootprint(oldCarbonfootprint);
//            response.setResult(true);
//        }
//        return response;
//    }

//    /**
//     * Find all the carbon.
//     *
//     * @return returns the carbon
//     */
//
//    @RequestMapping("/carbon/findAll")
//    @ResponseBody
//    public String carbonFindAll() {
//        String result = "";
//
//        for (CO2 user : co2Repository.findAll()) {
//            result += user.toString() + "<br>";
//        }
//
//        return result;
//    }


}
