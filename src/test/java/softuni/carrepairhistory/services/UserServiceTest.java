package softuni.carrepairhistory.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import softuni.carrepairhistory.models.dto.UserRegistrationDto;
import softuni.carrepairhistory.models.dto.UserStatusDto;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;
import softuni.carrepairhistory.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private ModelMapper modelMapper;

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

        when(roleRepository.findByName(UserRoleEnum.USER))
                .thenReturn(new RoleEntity(UserRoleEnum.USER));

        when(userRepository.findByUsername(userRegistrationDto.getUsername()))
                .thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRegistrationDto.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .thenReturn("encodedpassword");

        // Act
        boolean result = userService.toRegister(userRegistrationDto);

        // Assert
        Assertions.assertTrue(result);
        verify(userRepository, times(1)).save(Mockito.any(UserEntity.class));
    }

    @Test
    public void testLoggedUser() {
        String username = "testUsername";
        UserEntity expectedUser = new UserEntity();
        expectedUser.setUsername(username);
        Optional<UserEntity> optionalUser = Optional.of(expectedUser);

        when(userRepository.findByUsername(username)).thenReturn(optionalUser);

        UserEntity result = userService.loggedUser(username);

        assertEquals(expectedUser, result);
        verify(userRepository).findByUsername(username);
    }


    @Test
    public void testGetAllUsers() {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("user1");
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("user2");
        List<UserEntity> expectedUserList = Arrays.asList(user1, user2);

        when(userRepository.findAllBy()).thenReturn(expectedUserList);

        UserStatusDto user1Dto = new UserStatusDto();
        user1Dto.setId(user1.getId());
        user1Dto.setUsername(user1.getUsername());
        UserStatusDto user2Dto = new UserStatusDto();
        user2Dto.setId(user2.getId());
        user2Dto.setUsername(user2.getUsername());
        List<UserStatusDto> expectedUserDtoList = Arrays.asList(user1Dto, user2Dto);

        when(modelMapper.map(user1, UserStatusDto.class)).thenReturn(user1Dto);
        when(modelMapper.map(user2, UserStatusDto.class)).thenReturn(user2Dto);

        List<UserStatusDto> result = userService.getAllUsers();

        assertEquals(expectedUserDtoList, result);
        verify(userRepository).findAllBy();
        verify(modelMapper).map(user1, UserStatusDto.class);
        verify(modelMapper).map(user2, UserStatusDto.class);
    }

//    @Test
//    public void testChangeAdminsRole() {
//        Long userId = 1L;
//        UserEntity user = new UserEntity();
//        user.setId(userId);
//        RoleEntity adminRole = new RoleEntity();
//        adminRole.setName(UserRoleEnum.ADMIN);
//        List<RoleEntity> userRoles = new ArrayList<>();
//        userRoles.add(adminRole);
//        user.setUserRoles(userRoles);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(roleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);
//
//        // Test changing admin role for a non-admin user
//        userId = 2L;
//        user = new UserEntity();
//        user.setId(userId);
//        userRoles = new ArrayList<>();
//        userRoles.add(new RoleEntity());
//        user.setUserRoles(userRoles);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        userService.changeAdminsRole(userId);
//        Assertions.assertFalse(user.getUserRoles().contains(adminRole));
//        verify(userRepository).save(user);
//
////        // Test changing admin role for an admin user
//        userId = 1L;
//        user = new UserEntity();
//        user.setId(userId);
//        userRoles = new ArrayList<>();
//        userRoles.add(adminRole);
//        user.setUserRoles(userRoles);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        userService.changeAdminsRole(userId);
//        Assertions.assertTrue(user.getUserRoles().contains(adminRole));
//
//
//        // Test changing admin role for the user with ID 1 (should not change)
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        userService.changeAdminsRole(userId);
//        Assertions.assertTrue(user.getUserRoles().contains(adminRole));
//
//    }
}
