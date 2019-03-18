package server;


import communication.Co2Request;
import communication.Co2Response;

import model.CO2;
import model.User;
import repository.CO2Repository;
import repository.UserRepository;
import util.CarbonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Co2Controller {

    @Autowired
    private CO2Repository co2Repository;

    @Autowired
    private UserRepository userRepository;
    /**
     * SpringBoot automatically wires the CO2Repository instance
     * into this class using this setter method
     * @param co2Repository - the CO@Repository instance
     */


    Logger log = LogManager.getLogger(Co2Controller.class.getName());



    @RequestMapping(value = "/carbon/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Co2Response> handleCarbonAdd(@RequestBody Co2Request req) {
        log.info("Entering check co2adddata"+req.getChoiceBoxValue());
        Co2Response res = addCo2Data(req);
        return new ResponseEntity<Co2Response>(res, HttpStatus.OK);
    }

    public Co2Response addCo2Data(Co2Request request) {
        log.info("Entering check co2adddata"+request.getChoiceBoxValue());
        Co2Response response= new Co2Response();
        String cusername=request.getCusername();
        response.setUsername(cusername);
        //Validate whether the input user exists in the user table
        List<User> userList=userRepository.findByUsername(cusername);
        if(userList==null|| userList.isEmpty()){
            //This condition gets executed if user is not available in the system. Hence the carbon footprint cannot be added.
            response.setResult(false);
            return response;
        }
        //Calculate the pre-defined carbon footprint
        int currentCarbonfootprint= CarbonUtil.getCarbonfootprint(request.getChoiceBoxValue());

        List<CO2> dbUserList = co2Repository.findByCusername(cusername);
        if(dbUserList!=null&& !dbUserList.isEmpty()){
            //This condition gets executed if a user is already having a carbon footprint
            CO2 dbUser = dbUserList.get(0);
            int oldCarbonfootprint = dbUser.getCo2reduc();
            int newCarbonfootprint=oldCarbonfootprint+currentCarbonfootprint;
            dbUser.setCo2reduc(newCarbonfootprint);
            co2Repository.save(dbUser);
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        }
        else{
            //This condition gets executed if user is creating their first carbon footprint
            int oldCarbonfootprint = 0;
            int newCarbonfootprint=oldCarbonfootprint+currentCarbonfootprint;
            co2Repository.save(new CO2(cusername, newCarbonfootprint));
            response.setNewCarbonfootprint(newCarbonfootprint);
            response.setOldCarbonfootprint(oldCarbonfootprint);
            response.setResult(true);
        }

        return response;
    }

    @RequestMapping("/carbon/findAll")
    @ResponseBody
    public String carbonFindAll() {
        String result = "";

        for (CO2 user : co2Repository.findAll()) {
            result += user.toString() + "<br>";
        }

        return result;
    }


}
