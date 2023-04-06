package softuni.carrepairhistory.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.carrepairhistory.models.dto.CarInfoDto;
import softuni.carrepairhistory.models.dto.CreateCarDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private UserService userService;

    @Mock
    private RepairService repairService;

    @InjectMocks
    private CarService carService;

    private Principal principal;

    private CreateCarDto createCarDto;

    private Car car;

    @BeforeEach
    void setUp() {
        principal = mock(Principal.class);
        createCarDto = mock(CreateCarDto.class);
        car = mock(Car.class);
    }

    @Test
    void testRegisterCarWithExistingCar() {
        String registerNumber = "1234";
        when(createCarDto.getFuel()).thenReturn("GASOLINE");
        when(createCarDto.getRegisterNumber()).thenReturn(registerNumber);
        when(carRepository.findByRegisterNumber(registerNumber)).thenReturn(Optional.of(car));

        boolean result = carService.registerCar(principal, createCarDto);

        assertFalse(result);
        verify(carRepository).findByRegisterNumber(registerNumber);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(car);
    }

    @Test
    void testRegisterCarWithNewCar() {
        String registerNumber = "1234";
        when(createCarDto.getFuel()).thenReturn("GASOLINE");
        when(createCarDto.getRegisterNumber()).thenReturn(registerNumber);
        when(userService.loggedUser(principal.getName())).thenReturn(mock(UserEntity.class));
        when(carRepository.findByRegisterNumber(registerNumber)).thenReturn(Optional.empty());

        boolean result = carService.registerCar(principal, createCarDto);

        assertTrue(result);
        verify(carRepository).findByRegisterNumber(registerNumber);
        verify(carRepository).save(any(Car.class));
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(car);
    }

    @Test
    void testMapToCarInfo() {
        Long carId = 1L;
        String brand = "BMW";
        String model = "X5";
        String registerNumber = "1234";
        double totalRepairPrice = 1000.00;
        List<Repair> repairList = new ArrayList<>();
        Repair repair1 = new Repair();
        repair1.setPrice(BigDecimal.valueOf(500.00));
        Repair repair2 = new Repair();
        repair2.setPrice(BigDecimal.valueOf(500.00));
        repairList.add(repair1);
        repairList.add(repair2);

        when(car.getId()).thenReturn(carId);
        when(car.getBrand()).thenReturn(brand);
        when(car.getModel()).thenReturn(model);
        when(car.getRegisterNumber()).thenReturn(registerNumber);
        when(repairRepository.findAllByCarId(carId)).thenReturn(repairList);

        CarInfoDto result = carService.mapToCarInfo(car);

        assertNotNull(result);
        assertEquals(carId, result.getId());
        assertEquals(brand, result.getBrand());
        assertEquals(model, result.getModel());
        assertEquals(repairList.size(), result.getRepairsCount());
        assertEquals(registerNumber, result.getRegisterNumber());
        assertEquals(BigDecimal.valueOf(totalRepairPrice), result.getTotalRepairPrice());
    }

    @Test
    void testRemoveCar() throws Exception {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Act
        carService.removeCar(carId);

        // Assert

        verify(carRepository, times(1)).deleteById(carId);
    }

}
