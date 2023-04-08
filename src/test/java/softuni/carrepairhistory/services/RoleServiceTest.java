package softuni.carrepairhistory.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;


//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<RoleEntity> roleEntityCaptor;

    @InjectMocks
    private RoleService roleService;

//    @Before
//    public void setUp() {
//        // Setup any necessary test data
//    }

    @Test
    public void initAdminRole_shouldSaveAdminRole() {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(UserRoleEnum.ADMIN);


        roleService.initAdminRole();


        Mockito.verify(roleRepository, Mockito.times(1)).save(roleEntityCaptor.capture());
        Assertions.assertTrue(roleEntityCaptor.getValue().getName().equals(roleEntity.getName()));
    }

    @Test
    public void initUserRole_shouldSaveUserRole() {
        // Setup
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(UserRoleEnum.USER);

        // Execute
        roleService.initUserRole();

        // Verify
        Mockito.verify(roleRepository, Mockito.times(1)).save(roleEntityCaptor.capture());
        Assertions.assertTrue(roleEntityCaptor.getValue().getName().equals(roleEntity.getName()));
    }
}
