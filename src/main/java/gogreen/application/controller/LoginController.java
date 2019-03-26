package gogreen.application.controller;

import gogreen.application.communication.LoginData;
import gogreen.application.communication.LoginRequest;
import gogreen.application.communication.LoginResponse;
import gogreen.application.model.User;
import gogreen.application.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
public class LoginController {

    static Logger log = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private UserRepository userRepository;

    public LoginController() {

    }

    /**
     * Adds a page /login which handles responding to login requests.
     *
     * @param req - LoginRequest object containing login data
     * @return - responds either with 'HTTP 200 OK' or 'HTTP 401 Unauthorized' on an authorization
     * success or failure respectively.
     */
    @PostMapping(value = "/login",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity handleLoginRequest(@RequestBody LoginRequest req) {
        if (checkLoginData(req.getLoginData(), userRepository)){
            // login successful
            return new ResponseEntity(HttpStatus.OK);
        }

        // login unsuccessful
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Checks the login username and password.
     *
     * @param loginData - LoginData object containing the users login credentials.
     * @param userRepository - the repository storing users to check.
     * @return returns true iff the user/password combination exists in the database.
     */
    public static boolean checkLoginData(LoginData loginData, UserRepository userRepository) {
        List<User> userDB = userRepository.findByUsername(loginData.getUsername());

        if (userDB == null || userDB.isEmpty()) {
            // user does not have a valid record the database
            return false;
        }

        for (User user : userDB) {
            if (loginData.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
