package server;

import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static server.ServerApplication.checkLoginData;

@Controller
public class RegistrationController {
    /**
     * Adds a page /login which handles responding to login requests
     */
    @RequestMapping(value = "/registration",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<LoginResponse> handleLoginRequest(@RequestBody LoginRequest req) {
        boolean success = checkLoginData(req);
        LoginResponse res = new LoginResponse(success);
        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
    }
}
