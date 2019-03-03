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
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Howdy client!";
    }
}
