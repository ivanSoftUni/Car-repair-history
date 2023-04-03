package softuni.carrepairhistory.services;


import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.models.dto.UserStatusDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final String defaultPassword;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(RoleRepository roleRepository, RoleService roleService, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService,
                       @Value("${app.default.password}") String defaultPassword, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.defaultPassword = defaultPassword;
        this.modelMapper = modelMapper;
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
        admin.setUsername("admin");
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


    public UserEntity loggedUser(String username) {

        Optional<UserEntity> user = this.userRepository.findByUsername(username);

        return user.get();
    }

    public List<UserStatusDto> getAllUsers() {
        List<UserEntity> userEntityList = this.userRepository.findAllBy();

        List<UserStatusDto> allUsers = userEntityList.stream().map(user -> modelMapper.map(user, UserStatusDto.class)).toList();

        return allUsers;
    }

    @Transactional
    public void changeAdminsRole(Long id) {

        UserEntity user = this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with ID " + id + " not found", id));

        RoleEntity userRole = this.roleRepository.findByRole(UserRoleEnum.ADMIN);

        List<RoleEntity> userRoles = user.getUserRoles();

        RoleEntity roleEntity = userRoles.stream().filter(u -> u.getRole().equals(userRole.getRole())).findFirst().orElse(null);

        if (roleEntity != null) {
            userRoles.remove(userRole);
        } else {
            userRoles.add(userRole);
        }

        this.userRepository.save(user);

    }
}
