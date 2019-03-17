package application.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return "Howdy client!";
    }
}
