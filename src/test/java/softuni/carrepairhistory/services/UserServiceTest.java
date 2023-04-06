package softuni.carrepairhistory.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser() {
        // Arrange
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("testuser");
        userRegistrationDto.setEmail("testuser@example.com");
        userRegistrationDto.setPassword("testpassword");
        userRegistrationDto.setConfirmPassword("testpassword");

        Mockito.when(roleRepository.findByRole(UserRoleEnum.USER))
                .thenReturn(new RoleEntity(UserRoleEnum.USER));

        Mockito.when(userRepository.findByUsername(userRegistrationDto.getUsername()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(userRegistrationDto.getEmail()))
                .thenReturn(Optional.empty());

        Mockito.when(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .thenReturn("encodedpassword");

        // Act
        boolean result = userService.toRegister(userRegistrationDto);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
    }
}
