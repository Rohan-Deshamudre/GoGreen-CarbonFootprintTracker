package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ServerApplication {

    // Starts the spring application
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
