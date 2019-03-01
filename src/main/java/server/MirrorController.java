package server;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MirrorController {
    /**
     * Adds a page /test/status which responds an up message
     */
    @RequestMapping("/test/status")
    @ResponseBody
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("{\"status\" : \"UP\"}", HttpStatus.OK);
    }

    /**
     * Adds a page /test/mirror which responds by mirroring the request
     */
    @RequestMapping("/test/mirror")
    @ResponseBody
    public ResponseEntity<String> mirror(@RequestBody String req) {
        return new ResponseEntity<>(req, HttpStatus.OK);
    }
}
