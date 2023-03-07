package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.CreateCarDto;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.services.CarService;

import java.security.Principal;

@Controller
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;


    @Autowired
    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;

        this.carService = carService;
    }

    @ModelAttribute
    public CreateCarDto createCarDto() {
        return new CreateCarDto();
    }

    @GetMapping("/car/add")
    public String addCar() {

        return "add-car";
    }

    @PostMapping("/car/add")
    public String createCar(@Valid CreateCarDto createCarDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {

        if (bindingResult.hasErrors() || !this.carService.registerCar(principal, createCarDto)) {

            redirectAttributes.addFlashAttribute("createCarDto", createCarDto);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createCarDto", bindingResult);

            return "redirect:/car/add";
        }


        return "redirect:/users/home";
    }
}
