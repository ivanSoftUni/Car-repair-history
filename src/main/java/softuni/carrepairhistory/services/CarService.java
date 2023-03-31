package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.CarInfoDto;
import softuni.carrepairhistory.models.dto.CreateCarDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.enums.CarFuels;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;


import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final RepairService repairService;

    private final UserService userService;
    private final RepairRepository repairRepository;

    @Autowired
    public CarService(CarRepository carRepository, RepairService repairService, UserService userService, RepairRepository repairRepository) {
        this.carRepository = carRepository;
        this.repairService = repairService;
        this.userService = userService;
        this.repairRepository = repairRepository;
    }


    public boolean registerCar(Principal principal, CreateCarDto createCarDto) {

        CarFuels carFuels = CarFuels.valueOf(createCarDto.getFuel());

        Optional<Car> existCar = this.carRepository.findByRegisterNumber(createCarDto.getRegisterNumber());

        if (existCar.isPresent()) {
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

    public CarInfoDto mapToCarInfo(Car car) {

        List<Repair> repairList = this.repairRepository.findAllByCarId(car.getId());
        double sum = repairList.stream().map(Repair::getPrice).mapToDouble(BigDecimal::doubleValue).sum();


        CarInfoDto carInfoDto = new CarInfoDto();

        carInfoDto.setId(car.getId());
        carInfoDto.setBrand(car.getBrand());
        carInfoDto.setModel(car.getModel());
        carInfoDto.setRepairsCount(repairList.size());
        carInfoDto.setRegisterNumber(car.getRegisterNumber());
        carInfoDto.setTotalRepairPrice(BigDecimal.valueOf(sum));


        return carInfoDto;
    }

    public void removeCar(Long id) {

        Optional<Car> car = this.carRepository.findById(id);
        if (car.isPresent()) {
            List<Repair> allRepairsByCarId = this.repairService.getAllRepairsByCarId(id);
            for (Repair repair : allRepairsByCarId) {
                repairService.removeRepair(repair.getId());
            }
            this.carRepository.deleteById(id);

        }
    }
}
