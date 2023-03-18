package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.AddVehicleShop;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.repositories.*;
import softuni.carrepairhistory.services.VehicleShopService;

import java.security.Principal;
import java.util.List;

@Controller
public class RepairController {

    private final PartsCategoryRepository categoryRepository;
    private final RepairRepository repairRepository;
    private final CarRepository carRepository;
    private final VehiclesRepairsShopRepository repairsShopRepository;
    private final UserRepository userRepository;
    private final VehicleShopService vehicleShopService;

    @Autowired
    public RepairController(PartsCategoryRepository categoryRepository, RepairRepository repairRepository, CarRepository carRepository, VehiclesRepairsShopRepository repairsShopRepository, UserRepository userRepository, VehicleShopService vehicleShopService) {
        this.categoryRepository = categoryRepository;
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;

        this.repairsShopRepository = repairsShopRepository;
        this.userRepository = userRepository;
        this.vehicleShopService = vehicleShopService;
    }

    @ModelAttribute("addRepairDto")
    public AddRepairDto initForm() {
        return new AddRepairDto();
    }

    @ModelAttribute("addVehicleShop")
    public AddVehicleShop initVehicleShop() {
        return new AddVehicleShop();
    }

    @GetMapping("/repair/add")
    public String getRepair(Model model, Principal principal) {

        List<PartsCategory> categoryList = this.categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        UserEntity user = this.userRepository.findByUsername(principal.getName()).get();

        List<Car> cars = this.carRepository.findByUserEntityId(user.getId());
        model.addAttribute("cars", cars);


        return "add-repair";
    }

    @PostMapping("/repair/add")
    public String addRepair(@Valid AddRepairDto addRepairDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRepairDto", addRepairDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addRepairDto", bindingResult);


            return "redirect:/repair/add";
        }

        return "redirect:/users/home";
    }

    @GetMapping("/vehicle-shop/add")
    public String vehicleShop() {

        return "add-vehicle-shop";
    }

    @PostMapping("/vehicle-shop/add")
    public String addVehicleShop(@Valid AddVehicleShop addVehicleShop,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal) {

        if (bindingResult.hasErrors() || !this.vehicleShopService.createVehicleShop(addVehicleShop, principal)) {
            redirectAttributes.addFlashAttribute("addVehicleShop", addVehicleShop);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addVehicleShop", bindingResult);

            return "redirect:/vehicle-shop/add";
        }


        return "redirect:/users/home";
    }
}