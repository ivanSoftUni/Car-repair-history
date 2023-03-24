package softuni.carrepairhistory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.carrepairhistory.models.dto.CarInfoDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.services.CarService;
import softuni.carrepairhistory.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final CarRepository carRepository;
    private final CarService carService;
    private final RepairRepository repairRepository;

    @Autowired
    public HomeController(UserService userService, CarRepository carRepository, CarService carService, RepairRepository repairRepository) {
        this.userService = userService;
        this.carRepository = carRepository;
        this.carService = carService;
        this.repairRepository = repairRepository;
    }

    @GetMapping("/users/all/cars")
    public String getHome(Model model, Principal principal) {

        UserEntity user = userService.loggedUser(principal.getName());

        List<CarInfoDto> carsInfo = this.carRepository.findByUserEntityId(user.getId())
                .stream()
                .map(carService::mapToCarInfo)
                .toList();

        model.addAttribute("carsInfo", carsInfo);

        return "all-cars";
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }
}
