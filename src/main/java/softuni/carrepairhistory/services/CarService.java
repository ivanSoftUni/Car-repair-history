package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.CreateCarDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.enums.CarFuels;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }


    public boolean registerCar(Principal principal, CreateCarDto createCarDto) {

        Optional<UserEntity> user = this.userRepository.findByUsername(principal.getName());
        CarFuels carFuels = CarFuels.valueOf(createCarDto.getFuel());

        Car car = new Car();
        car.setBrand(createCarDto.getBrand());
        car.setModel(createCarDto.getModel());
        car.setMadeYear(createCarDto.getMadeYear());
        car.setHorsePower(createCarDto.getHorsePower());
        car.setEngine(createCarDto.getEngine());
        car.setRegisterNumber(createCarDto.getRegisterNumber());
        car.setUserEntity(user.get());
        car.setFuel(carFuels);
        System.out.println();
        this.carRepository.save(car);


        return true;
    }
}
