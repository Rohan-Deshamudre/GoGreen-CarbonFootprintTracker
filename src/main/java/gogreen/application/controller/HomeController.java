package gogreen.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    /**
     * Home.
     * @return returns the method
     */
    @RequestMapping("/")
    @ResponseBody
    public static String home() {
        return "Please Log In:";
    }
}
