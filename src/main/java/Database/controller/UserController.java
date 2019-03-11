package Database.controller;

import Database.model.User;
import Database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/save")
    public String save() {
        // save a single Customer
        repository.save(new User("Jack", "hello"));

        return "Done";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "";

        for (User user : repository.findAll()) {
            result += user.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/findByUsername")
    public String fetchDataByUsername(@RequestParam("username") String username) {
        String result = "";

        for (User user : repository.findByUsername(username)) {
            result += user.toString() + "<br>";
        }
        return result;
    }
}
