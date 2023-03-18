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
import java.util.Optional;

@Service
public class VehicleShopService {

    private final UserRepository userRepository;
    private VehiclesRepairsShopRepository vehiclesRepairsShopRepository;

    @Autowired
    public VehicleShopService(UserRepository userRepository, VehiclesRepairsShopRepository vehiclesRepairsShopRepository) {
        this.userRepository = userRepository;
        this.vehiclesRepairsShopRepository = vehiclesRepairsShopRepository;
    }

    public boolean createVehicleShop(AddVehicleShop addVehicleShop,
                                     Principal principal) {
        UserEntity user = this.userRepository.findByUsername(principal.getName()).get();

        VehiclesRepairsShop vehiclesRepairsShop = new VehiclesRepairsShop();
        vehiclesRepairsShop.setName(addVehicleShop.getName());
        vehiclesRepairsShop.setCity(addVehicleShop.getCity());
        vehiclesRepairsShop.setAddress(addVehicleShop.getAddress());
        vehiclesRepairsShop.setPhoneNumber(addVehicleShop.getPhoneNumber());
        vehiclesRepairsShop.setUser(user);
        vehiclesRepairsShop.setCars(new ArrayList<>());

        this.vehiclesRepairsShopRepository.save(vehiclesRepairsShop);

        return true;
    }
}
