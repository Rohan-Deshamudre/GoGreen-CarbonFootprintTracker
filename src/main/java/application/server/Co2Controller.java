package application.server;

import application.repository.CO2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Co2Controller {

    @Autowired
    private CO2Repository repository;

}
