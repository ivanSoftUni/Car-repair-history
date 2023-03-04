package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
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
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
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
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(UserRoleEnum.USER);


        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegistrationDto.getUsername());
        userEntity.setEmail(userRegistrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        userEntity.setUserRoles(List.of());
        userEntity.addRole(roleEntity);
        this.userRepository.save(userEntity);

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
