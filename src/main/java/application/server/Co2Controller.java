package application.server;


import application.communication.Co2Request;
import application.communication.Co2Response;

import application.model.CO2;
import application.repository.CO2Repository;
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


@RestController
public class Co2Controller {

    @Autowired
    private CO2Repository co2Repository;
    /**
     * SpringBoot automatically wires the CO2Repository instance
     * into this class using this setter method
     * @param co2Repository - the CO@Repository instance
     */


    Logger log = LogManager.getLogger(Co2Controller.class.getName());



    @RequestMapping(value = "/carbon",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Co2Response> handleCarbonAdd(@RequestBody Co2Request req) {
        log.info("Entering check co2adddata"+req.getChoiceBoxValue());
        boolean result = addCo2Data(req);
        Co2Response res = new Co2Response(result);
        return new ResponseEntity<Co2Response>(res, HttpStatus.OK);
    }

    public boolean addCo2Data(Co2Request request) {
        log.info("Entering check co2adddata"+request.getChoiceBoxValue());
        String cusername="Jack";
        int co2reduc=200;
        co2Repository.save(new CO2(cusername, co2reduc));

        return false;
    }



    @RequestMapping("/findByCusername")
    @ResponseBody
    public CO2 findReduc(String cusername) {
        CO2 newUser = null;

        for(CO2 co2 : co2Repository.findByCusername(cusername)) {
            newUser = co2;
        }

        return newUser;
    }
}
