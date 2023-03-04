package softuni.carrepairhistory.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.services.RoleService;

@Component
public class UserRolesInit implements CommandLineRunner {


    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserRolesInit(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {



    }
}
