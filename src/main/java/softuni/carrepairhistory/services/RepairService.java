package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.repositories.UserRepository;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

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


    public boolean createRepair(AddRepairDto addRepairDto,
                                Principal principal) {

        Optional<Car> car = this.carRepository.findById(addRepairDto.getCarId());
        Optional<VehiclesRepairsShop> shop = this.vehiclesRepairsShopRepository.findById(addRepairDto.getVehicleShopId());
        UserEntity user = this.userRepository.findByUsername(principal.getName()).get();

        if (car.isEmpty() || shop.isEmpty()) {
            return false;
        }

        Repair repair = new Repair();

        repair.setDescription(addRepairDto.getDescription());
        repair.setDate(addRepairDto.getDate());
        repair.setComment(addRepairDto.getComment());
        repair.setPrice(addRepairDto.getPrice());
        repair.setParts(new ArrayList<>());

        repair.setCar(car.get());
        repair.setRepairsShop(shop.get());
        repair.setUser(user);

        this.repairRepository.save(repair);

        shop.get().getCars().add(car.get());
        user.getRepairs().add(repair);


        return true;
    }

    public RepairDetailDto mapToRepairDetail(Repair repair) {

        RepairDetailDto repairDetailDto = new RepairDetailDto();

        repairDetailDto.setId(repair.getId());
        repairDetailDto.setCarInfo(String.format(repair.getCar().getBrand() + " " + repair.getCar().getModel()));
        repairDetailDto.setRepairTitle(repair.getDescription());
        repairDetailDto.setDate(repair.getDate());
        repairDetailDto.setComment(repair.getComment());
        repairDetailDto.setPrice(repair.getPrice());
        repairDetailDto.setServiceShopName(repair.getRepairsShop().getName());


        return repairDetailDto;
    }

}
