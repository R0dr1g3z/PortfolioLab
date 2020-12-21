package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.security.User;
import pl.coderslab.security.UserService;

@Controller
@RequestMapping("/create")
public class CreateController {

    private final UserService userService;

    public CreateController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin")
    public String admin(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveAdmin(user);
        return "redirect:/";
    }
}
