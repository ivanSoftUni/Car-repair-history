package softuni.carrepairhistory.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import softuni.carrepairhistory.models.dto.AddVehicleShop;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

import java.security.Principal;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class VehicleShopServiceTest {
    private VehicleShopService vehicleShopService;

    @Mock
    private VehiclesRepairsShopRepository vehiclesRepairsShopRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleShopService = new VehicleShopService(vehiclesRepairsShopRepository, userService);
    }

    @Test
    void createVehicleShop_ShouldReturnTrue() {
        // Arrange
        AddVehicleShop addVehicleShop = new AddVehicleShop();
        addVehicleShop.setName("Test Shop");
        addVehicleShop.setCity("Test City");
        addVehicleShop.setAddress("Test Address");
        addVehicleShop.setPhoneNumber("1234567890");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");

        UserEntity user = new UserEntity();
        when(userService.loggedUser(principal.getName())).thenReturn(user);

        // Act
        boolean result = vehicleShopService.createVehicleShop(addVehicleShop, principal);

        // Assert
        assertTrue(result);
        verify(vehiclesRepairsShopRepository, times(1)).save(any(VehiclesRepairsShop.class));
    }
}
