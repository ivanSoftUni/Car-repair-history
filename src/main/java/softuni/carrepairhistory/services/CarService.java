package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.CreateCarDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.enums.CarFuels;
import softuni.carrepairhistory.repositories.CarRepository;


import java.security.Principal;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final UserService userService;

    @Autowired
    public CarService(CarRepository carRepository, UserService userService) {
        this.carRepository = carRepository;
        this.userService = userService;
    }


    public boolean registerCar(Principal principal, CreateCarDto createCarDto) {

        CarFuels carFuels = CarFuels.valueOf(createCarDto.getFuel());

        Optional<Car> optionalCar = this.carRepository.findByRegisterNumber(createCarDto.getRegisterNumber());

        if (optionalCar.isPresent()) {
            return false;
        }

        Car car = new Car();
        car.setBrand(createCarDto.getBrand());
        car.setModel(createCarDto.getModel());
        car.setMadeYear(createCarDto.getMadeYear());
        car.setHorsePower(createCarDto.getHorsePower());
        car.setEngine(createCarDto.getEngine());
        car.setRegisterNumber(createCarDto.getRegisterNumber());
        car.setUserEntity(userService.loggedUser(principal.getName()));
        car.setFuel(carFuels);

        this.carRepository.save(car);


        return true;
    }
}
