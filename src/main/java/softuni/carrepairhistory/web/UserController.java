package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserRegistrationDto registerDTO() {
        return new UserRegistrationDto();
    }

    @GetMapping("/users/register")
    public String register() {

        return "register";
    }

    @PostMapping("/users/register")
    public String registerUser(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.toRegister(userRegistrationDto)) {

            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:/users/register";
        }


        return "redirect:/users/login";
    }


    @GetMapping("/users/login")
    public String login() {

        return "login";
    }
}
