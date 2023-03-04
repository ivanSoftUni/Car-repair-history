package softuni.carrepairhistory.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carrepairhistory.repositories.PartRepository;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.services.PartsCategoryService;
import softuni.carrepairhistory.services.PartsService;
import softuni.carrepairhistory.services.RoleService;
import softuni.carrepairhistory.services.UserService;


@Component
public class PartsInit implements CommandLineRunner {

    private final PartsCategoryService partsCategoryService;
    private final PartsService partsService;
    private final PartsCategoryRepository partsCategoryRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    private final PartRepository partRepository;

    @Autowired
    public PartsInit(PartsCategoryService partsCategoryService, PartsService partsService, PartsCategoryRepository partsCategoryRepository, UserService userService, RoleRepository roleRepository, RoleService roleService, PartRepository partRepository) {
        this.partsCategoryService = partsCategoryService;
        this.partsService = partsService;
        this.partsCategoryRepository = partsCategoryRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.partRepository = partRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (partsCategoryRepository.count() == 0 && partRepository.count() == 0) {
            this.partsCategoryService.initBasicCategory();
            this.partsService.initBasicPart();
        }

        this.userService.init();


    }
}
