package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private repository.UserRepository UserRepository;

//    @RequestMapping("/save")
//    @ResponseBody
//    public String save(@RequestParam String username, @RequestParam String password) {
//        // save a single Customer
//        UserRepository.save(new User(username, password));
//
//        return "Done";
//    }
//
//    @RequestMapping("/findAll")
//    @ResponseBody
//    public String findAll() {
//        String result = "";
//
//        for (User user : UserRepository.findAll()) {
//            result += user.toString() + "<br>";
//        }
//
//        return result;
//    }
//
//    @RequestMapping("/findByUsername")
//    @ResponseBody
//    public String findByUsername(@RequestParam("Jack") String username) {
//        String result = "";
//
//        for (User user : UserRepository.findByUsername(username)) {
//            result += user.toString() + "<br>";
//        }
//        return result;
//    }




}
