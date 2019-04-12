package gogreen.application.controller;

import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.User;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CO2Repository co2Repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Adds a page /login which handles responding to login requests.
     *
     * @param cred - LoginData object containing login credentials.
     * @return - responds either with 'HTTP 200 OK' or 'HTTP 401 Unauthorized' on an authorization
     *      success or failure respectively.
     */
    @PostMapping(value = "/login",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity handleLoginRequest(@RequestBody LoginData cred) {
        if (checkLoginData(cred, userRepository, passwordEncoder)) {
            // login successful
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Adds a page /login/register which handles the registration of new accounts.
     *
     * @param cred - LoginData object containing login credentials for the new account.
     * @return - responds either with 'HTTP 201 Created' or 'HTTP 403 Forbidden' on a registration
     *      success or failure respectively.
     */
    @PostMapping(value = "/login/register",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity handleRegisterRequest(@RequestBody LoginData cred) {
        if (!userRepository.findByUsername(cred.getUsername()).isEmpty()) {
            // Username is taken
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        // Register new account
        String encryptedPassword = passwordEncoder.encode(cred.getPassword());
        userRepository.save(new User(cred.getUsername(),encryptedPassword));
        co2Repository.save(new CO2(cred.getUsername(), 0, 0, 0, 0));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Checks the login credentials.
     *
     * @param loginData      - LoginData object containing the users login credentials.
     * @param userRepository - the repository storing users to check.
     * @param passwordEncoder - a spring passwordencoder instance
     * @return - true iff login is successful.
     */
    public static boolean checkLoginData(
            LoginData loginData, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        List<User> userDb = userRepository.findByUsername(loginData.getUsername());

        for (User user : userDb) {
            if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
