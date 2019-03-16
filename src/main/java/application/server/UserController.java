package application.server;

import application.model.User;
import application.repository.CO2Repository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private CO2Repository co2Repository;

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam String username, @RequestParam String password) {
        // save a single Customer
        repository.save(new User(username, password));

        return "Done";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll() {
        String result = "";

        for (User user : repository.findAll()) {
            result += user.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/findByUsername")
    @ResponseBody
    public String findByUsername(@RequestParam("Jack") String username) {
        String result = "";

        for (User user : repository.findByUsername(username)) {
            result += user.toString() + "<br>";
        }
        return result;
    }




}
