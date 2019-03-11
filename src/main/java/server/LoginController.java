package server;

import static server.ServerApplication.checkLoginData;
import communication.clientMessage.LoginRequest;
import communication.serverMessage.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    /**
     * Adds a page /login which handles responding to login requests.
     */
    @RequestMapping(value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<LoginResponse> handleLoginRequest(@RequestBody LoginRequest req) {
        /*
         * Needs to be connected to the database
         */
        boolean success = checkLoginData(req.getloginData());

        LoginResponse res = new LoginResponse(success);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
