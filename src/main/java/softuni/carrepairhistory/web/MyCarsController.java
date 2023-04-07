package softuni.carrepairhistory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import softuni.carrepairhistory.models.dto.CarInfoDto;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.exception.ObjectNotFoundException;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.services.CarService;
import softuni.carrepairhistory.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MyCarsController {

    private final UserService userService;
    private final CarRepository carRepository;
    private final CarService carService;

    @Autowired
    public MyCarsController(UserService userService, CarRepository carRepository, CarService carService) {
        this.userService = userService;
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @GetMapping("/users/all/cars")
    public String getAllUserCars(Model model, Principal principal) {

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
        try {
            this.carService.removeCar(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException(id, "Кола");
        }

        return "redirect:/users/all/cars";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView onCarNotFound(ObjectNotFoundException notFoundException) {
        ModelAndView modelAndView = new ModelAndView("not-found-error");

        modelAndView.addObject("objectId", notFoundException.getObjectId());
        modelAndView.addObject("objectType", notFoundException.getObjectType());

        return modelAndView;
    }

}
