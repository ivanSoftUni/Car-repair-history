package softuni.carrepairhistory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.carrepairhistory.models.dto.UserStatusDto;
import softuni.carrepairhistory.services.UserService;

import java.util.List;

@Controller
public class UserRoleController {


    private final UserService userService;

    @Autowired
    public UserRoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String getAdminPage(Model model) {

        List<UserStatusDto> allUsers = this.userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);


        return "admin";
    }
}
