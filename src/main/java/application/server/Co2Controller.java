package application.server;

import application.model.CO2;
import application.repository.CO2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Co2Controller {

    @Autowired
    private CO2Repository repository;

    @RequestMapping("/findByCusername")
    @ResponseBody
    public CO2 findReduc(String cusername) {
        CO2 newUser = null;

        for(CO2 co2 : repository.findByCusername(cusername)) {
            newUser = co2;
        }

        return newUser;
    }
}
