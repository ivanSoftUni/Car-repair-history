package softuni.carrepairhistory.services;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RepairServiceTest {

    @Mock
    private CarRepository carRepositoryMock;

    @Mock
    private RepairRepository repairRepositoryMock;

    @Mock
    private VehiclesRepairsShopRepository vehiclesRepairsShopRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private RepairService repairService;

    @Test
    public void createRepair_ShouldReturnFalse_WhenCarNotFound() {
        // Arrange
        AddRepairDto addRepairDto = new AddRepairDto();
        addRepairDto.setCarId(1L);
        addRepairDto.setVehicleShopId(1L);
        Principal principal = Mockito.mock(Principal.class);

        when(carRepositoryMock.findById(addRepairDto.getCarId())).thenReturn(Optional.empty());

        // Act
        boolean result = repairService.createRepair(addRepairDto, principal);

        // Assert
        assertFalse(result);
    }

    @Test
    public void createRepair_ShouldReturnFalse_WhenShopNotFound() {
        // Arrange
        AddRepairDto addRepairDto = new AddRepairDto();
        addRepairDto.setCarId(1L);
        addRepairDto.setVehicleShopId(1L);
        Principal principal = Mockito.mock(Principal.class);

        when(carRepositoryMock.findById(addRepairDto.getCarId())).thenReturn(Optional.of(Mockito.mock(Car.class)));
        when(vehiclesRepairsShopRepositoryMock.findById(addRepairDto.getVehicleShopId())).thenReturn(Optional.empty());

        // Act
        boolean result = repairService.createRepair(addRepairDto, principal);

        // Assert
        assertFalse(result);
    }

    @Test
    public void createRepair_ShouldReturnTrue_WhenValidData() {
        // Arrange
        AddRepairDto addRepairDto = new AddRepairDto();
        addRepairDto.setCarId(1L);
        addRepairDto.setVehicleShopId(1L);
        addRepairDto.setDescription("Test repair");
        addRepairDto.setDate(LocalDate.parse("2023-01-23"));
        addRepairDto.setComment("Test comment");
        addRepairDto.setPrice(BigDecimal.valueOf(100));
        Principal principal = Mockito.mock(Principal.class);

        when(carRepositoryMock.findById(addRepairDto.getCarId())).thenReturn(Optional.of(Mockito.mock(Car.class)));
        when(vehiclesRepairsShopRepositoryMock.findById(addRepairDto.getVehicleShopId())).thenReturn(Optional.of(Mockito.mock(VehiclesRepairsShop.class)));
        when(userServiceMock.loggedUser(principal.getName())).thenReturn(Mockito.mock(UserEntity.class));

        // Act
        boolean result = repairService.createRepair(addRepairDto, principal);

        // Assert
        assertTrue(result);
        Mockito.verify(repairRepositoryMock, Mockito.times(1)).save(Mockito.any(Repair.class));
    }

    @Test
    public void testGetAllRepairsByCarId() {
        // Arrange
        Long carId = 1L;
        List<Repair> repairs = new ArrayList<>();
        repairs.add(new Repair());
        repairs.add(new Repair());
        repairs.add(new Repair());

        when(repairRepositoryMock.findAllByCarId(anyLong())).thenReturn(repairs);

        // Act
        List<Repair> result = repairService.getAllRepairsByCarId(carId);

        // Assert
        assertEquals(repairs.size(), result.size());
    }

    @Test
    public void testMapToRepairDetail() {
        //Arrange
        Repair repair = new Repair();
        Car car = new Car();
        car.setBrand("Audi");
        car.setModel("A4");
        car.setRegisterNumber("CA1234AB");
        VehiclesRepairsShop shop = new VehiclesRepairsShop();
        shop.setName("Audi Service");
        repair.setId(1L);
        repair.setDescription("Oil change");
        repair.setDate(LocalDate.of(2022, 1, 1));
        repair.setComment("No additional comments");
        repair.setPrice(BigDecimal.valueOf(50));
        repair.setCar(car);
        repair.setRepairsShop(shop);

        RepairService repairService = new RepairService(null, null, null, null);

        //Act
        RepairDetailDto result = repairService.mapToRepairDetail(repair);

        //Assert
        assertEquals(repair.getId(), result.getId());
        assertEquals("Audi A4 - CA1234AB", result.getCarInfo());
        assertEquals("Oil change", result.getRepairTitle());
        assertEquals(LocalDate.of(2022, 1, 1), result.getDate());
        assertEquals("No additional comments", result.getComment());
        assertEquals(BigDecimal.valueOf(50), result.getPrice());
        assertEquals("Audi Service", result.getServiceShopName());
    }
}
