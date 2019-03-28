package gogreen.application.controller;

import static gogreen.application.controller.LoginController.checkLoginData;

import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.AddHomeTempRequest;
import gogreen.application.communication.AddLocalProduceRequest;
import gogreen.application.communication.AddSolarPanelRequest;
import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.CO2Response;
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
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid. Else returns a
     *      CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/food/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddFoodRequest req) {
        log.info(req.toString());

        if (!checkLoginData(req.getLoginData(), userRepository)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducFood = CarbonUtil.getFoodCarbonReduction(req.getChoiceBoxValue());
        userCO2.addCO2Food(carbonReducFood);
        userCO2.addCO2Reduc(carbonReducFood);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducFood), HttpStatus.OK);
    }

    /**
     * Handle add local produce requests.
     *
     * @param req - addLocalProduceRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid. Else returns a
     *      CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/localproduce/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleFoodAdd(@RequestBody AddLocalProduceRequest req) {
        log.info(req.toString());

        if (!checkLoginData(req.getLoginData(), userRepository)) {
            // session invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Update user's carbon food footprint reduction
        CO2 userCO2 = co2Repository.findByCusername(req.getLoginData().getUsername()).get(0);
        int carbonReducFood = CarbonUtil.getLocalProduceCarbonReduction(req.getWeight(), req.isOrganic());
        userCO2.addCO2Food(carbonReducFood);
        userCO2.addCO2Reduc(carbonReducFood);
        co2Repository.save(userCO2);

        return new ResponseEntity<>(new CO2Response(carbonReducFood), HttpStatus.OK);
    }

    /**
     * Handle add food requests.
     *
     * @param req - addFoodRequest containing the data for the request.
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid. Else returns a
     *      CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/transport/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleTransportAdd(@RequestBody AddTransportRequest req) {
        log.info(req.toString());

        if (!checkLoginData(req.getLoginData(), userRepository)) {
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
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid. Else returns a
     *      CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/hometemp/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleHomeTempAdd(@RequestBody AddHomeTempRequest req) {
        log.info(req.toString());

        if (!checkLoginData(req.getLoginData(), userRepository)) {
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
     * @return returns 'HTTP 401 Unauthorized' if the supplied login data is invalid. Else returns a
     *      CO2Response describing the amount the user's CO2 has been reduced.
     */
    @PostMapping(value = "/activity/solarpanel/add",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CO2Response> handleSolarPanAdd(@RequestBody AddSolarPanelRequest req) {
        log.info(req.toString());

        if (!checkLoginData(req.getLoginData(), userRepository)) {
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
}
