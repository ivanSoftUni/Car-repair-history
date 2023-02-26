package softuni.carrepairhistory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import softuni.carrepairhistory.models.dto.RegisterDTO;

@Controller
public class UserController {


    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @GetMapping("/users/register")
    public String register() {

        return "register";
    }


    @GetMapping("/users/login")
    public String login() {

        return "login";
    }
}
