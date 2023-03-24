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
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {
    private final CarRepository carRepository;
    private final RepairRepository repairRepository;
    private final VehiclesRepairsShopRepository vehiclesRepairsShopRepository;
    private final UserService userService;

    @Autowired
    public RepairService(CarRepository carRepository, RepairRepository repairRepository, VehiclesRepairsShopRepository vehiclesRepairsShopRepository, UserService userService) {
        this.carRepository = carRepository;
        this.repairRepository = repairRepository;
        this.vehiclesRepairsShopRepository = vehiclesRepairsShopRepository;
        this.userService = userService;
    }


    public boolean createRepair(AddRepairDto addRepairDto,
                                Principal principal) {

        Optional<Car> car = this.carRepository.findById(addRepairDto.getCarId());
        Optional<VehiclesRepairsShop> shop = this.vehiclesRepairsShopRepository.findById(addRepairDto.getVehicleShopId());


        if (car.isEmpty() || shop.isEmpty()) {
            return false;
        }

        Repair repair = new Repair();

        repair.setDescription(addRepairDto.getDescription());
        repair.setDate(addRepairDto.getDate());
        repair.setComment(addRepairDto.getComment());
        repair.setPrice(addRepairDto.getPrice());

        repair.setCar(car.get());
        repair.setRepairsShop(shop.get());
        repair.setUser(userService.loggedUser(principal.getName()));

//        car.get().getRepairList().add(repair);
//
//        shop.get().getCars().add(car.get());

        this.repairRepository.save(repair);
        System.out.println();

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
