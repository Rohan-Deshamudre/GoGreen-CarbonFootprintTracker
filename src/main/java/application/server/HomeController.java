package application.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Home.
     * @return returns the method
     */
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return "Please Log In:";
    }
}
