package softuni.carrepairhistory.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ApplicationUserDetailsServiceTest {

    private ApplicationUserDetailsService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new ApplicationUserDetailsService(userRepository);
    }


    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "unknownUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });
    }

}
