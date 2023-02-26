package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.UserRoleEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegistrationDto.getUsername());
        userEntity.setEmail(userRegistrationDto.getEmail());
        userEntity.setPassword(userRegistrationDto.getPassword());
        userEntity.setUserRoles(List.of());
        System.out.println();
        this.userRepository.saveAndFlush(userEntity);
        return true;
    }

}
