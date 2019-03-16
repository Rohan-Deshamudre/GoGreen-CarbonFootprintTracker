package application.server;

import application.communication.LoginRequest;
import application.communication.LoginResponse;
import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;



@Controller
public class LoginController {


    /**
     * SpringBoot automatically wires the UserRepository instance
     * into this class using this setter method
     * @param repository - the UserRepository instance
     */
    @Autowired
    private UserRepository userRepository;


    static Logger log = Logger.getLogger(LoginController.class.getName());

    /**
     * Adds a page /login which handles responding to login requests
     */

    @RequestMapping(value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<LoginResponse> handleLoginRequest(@RequestBody LoginRequest req) {
        log.info("Entering check handleLoginRequest");
        boolean result = checkLoginData(req.getUsername(), req.getPassword());
        LoginResponse res = new LoginResponse(result);
        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
    }


    public boolean checkLoginData(String username, String password) {
        System.out.println("Entering check logindata"+username);
        log.info("Entering check loginDataMethod");
        for (User user : userRepository.findByUsername(username)) {
            if (user == null) {
                return false;
            }
            else{
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                if(username.equalsIgnoreCase(user.getUsername())&& password.equals(user.getPassword())){
                    return true;
                }
            }

        }
        return false;
    }

}
