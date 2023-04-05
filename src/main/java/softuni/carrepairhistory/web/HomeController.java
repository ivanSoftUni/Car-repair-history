package softuni.carrepairhistory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import softuni.carrepairhistory.models.dto.CarInfoDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.services.CarService;
import softuni.carrepairhistory.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final CarRepository carRepository;
    private final CarService carService;

    @Autowired
    public HomeController(UserService userService, CarRepository carRepository, CarService carService) {
        this.userService = userService;
        this.carRepository = carRepository;
        this.carService = carService;
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

    @GetMapping("/delete/car/{id}")
    public String removeCar(@PathVariable Long id) {

        this.carService.removeCar(id);

        return "redirect:/users/all/cars";
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

}
