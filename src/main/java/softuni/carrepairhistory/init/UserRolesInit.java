package softuni.carrepairhistory.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carrepairhistory.services.UserService;

@Component
public class UserRolesInit implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserRolesInit(UserService userService) {

        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.init();

    }
}
