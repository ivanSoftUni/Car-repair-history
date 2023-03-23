package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.AddVehicleShop;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.UserRepository;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

import java.security.Principal;
import java.util.ArrayList;

@Service
public class VehicleShopService {


    private final VehiclesRepairsShopRepository vehiclesRepairsShopRepository;
    private final UserService userService;

    @Autowired
    public VehicleShopService(VehiclesRepairsShopRepository vehiclesRepairsShopRepository, UserService userService) {

        this.vehiclesRepairsShopRepository = vehiclesRepairsShopRepository;
        this.userService = userService;
    }

    public boolean createVehicleShop(AddVehicleShop addVehicleShop,
                                     Principal principal) {


        VehiclesRepairsShop vehiclesRepairsShop = new VehiclesRepairsShop();
        vehiclesRepairsShop.setName(addVehicleShop.getName());
        vehiclesRepairsShop.setCity(addVehicleShop.getCity());
        vehiclesRepairsShop.setAddress(addVehicleShop.getAddress());
        vehiclesRepairsShop.setPhoneNumber(addVehicleShop.getPhoneNumber());
        vehiclesRepairsShop.setUser(userService.loggedUser(principal.getName()));
        vehiclesRepairsShop.setCars(new ArrayList<>());

        this.vehiclesRepairsShopRepository.save(vehiclesRepairsShop);

        return true;
    }
}
