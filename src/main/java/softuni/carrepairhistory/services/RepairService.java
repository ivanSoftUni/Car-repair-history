package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.repositories.UserRepository;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

@Service
public class RepairService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RepairRepository repairRepository;
    private final VehiclesRepairsShopRepository vehiclesRepairsShopRepository;

    @Autowired
    public RepairService(CarRepository carRepository, UserRepository userRepository, RepairRepository repairRepository, VehiclesRepairsShopRepository vehiclesRepairsShopRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.repairRepository = repairRepository;
        this.vehiclesRepairsShopRepository = vehiclesRepairsShopRepository;
    }
}
