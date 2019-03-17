package server;

import static server.ServerApplication.checkLoginData;

import application.User;
import communication.clientMessage.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    /**
     * Adds a page /user which handles get requests for user statistics.
     * for now only sends a default user template (later on we will integrate
     * Database queries to find specific user statistics.
     */
    @RequestMapping(value = "/user",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<User> user(@RequestBody LoginRequest req) {
        if (!checkLoginData(req.getLoginData())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = new User("dummy", 420);


        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
