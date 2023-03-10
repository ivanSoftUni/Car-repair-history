package softuni.carrepairhistory.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private String defaultPassword;

    @Autowired
    public UserService(RoleRepository roleRepository, RoleService roleService, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService,
                       @Value("${app.default.password}") String defaultPassword) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.defaultPassword = defaultPassword;
    }

    public void init() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            this.roleService.initAdminRole();
            this.roleService.initUserRole();
            RoleEntity adminRole = this.roleRepository.findByRole(UserRoleEnum.ADMIN);
            createAdmin(List.of(adminRole));
        }

    }


    private void createAdmin(List<RoleEntity> roles) {
        UserEntity admin = new UserEntity();
        admin.setUserRoles(roles);
        admin.setUsername("Admin");
        admin.setEmail("admin@car.bg");
        admin.setPassword(passwordEncoder.encode(defaultPassword));

        userRepository.save(admin);
    }


    public boolean toRegister(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            return false;
        }
        RoleEntity userRole = this.roleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userRegistrationDto.getUsername());
        newUser.setEmail(userRegistrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        newUser.addRole(userRole);

        this.userRepository.save(newUser);

        return true;
    }

    public void login(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

}
