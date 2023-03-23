package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.AddVehicleShop;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.*;
import softuni.carrepairhistory.services.RepairService;
import softuni.carrepairhistory.services.VehicleShopService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RepairController {

    private final PartsCategoryRepository categoryRepository;
    private final RepairRepository repairRepository;
    private final CarRepository carRepository;
    private final VehiclesRepairsShopRepository repairsShopRepository;
    private final UserRepository userRepository;
    private final VehicleShopService vehicleShopService;
    private final VehiclesRepairsShopRepository vehiclesRepairsShopRepository;
    private final RepairService repairService;

    @Autowired
    public RepairController(PartsCategoryRepository categoryRepository, RepairRepository repairRepository, CarRepository carRepository, VehiclesRepairsShopRepository repairsShopRepository, UserRepository userRepository, VehicleShopService vehicleShopService, VehiclesRepairsShopRepository vehiclesRepairsShopRepository, RepairService repairService) {
        this.categoryRepository = categoryRepository;
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;

        this.repairsShopRepository = repairsShopRepository;
        this.userRepository = userRepository;
        this.vehicleShopService = vehicleShopService;
        this.vehiclesRepairsShopRepository = vehiclesRepairsShopRepository;
        this.repairService = repairService;
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

        List<VehiclesRepairsShop> vehicleShops = this.vehiclesRepairsShopRepository.findAllByUserId(user.getId());
        model.addAttribute("vehicleShops", vehicleShops);

        return "add-repair";
    }

    @PostMapping("/repair/add")
    public String addRepair(@Valid AddRepairDto addRepairDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {

        if (bindingResult.hasErrors() || !this.repairService.createRepair(addRepairDto, principal)) {
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

    @GetMapping("/repair/details")
    public String getDetails(Model model, Principal authenticatedPrincipal) {

        Optional<UserEntity> user = userRepository.findByUsername(authenticatedPrincipal.getName());

        List<RepairDetailDto> repairsDetails = this.repairRepository.findAllByUserId(user.get().getId())
                .stream()
                .map(repairService::mapToRepairDetail)
                .toList();

        model.addAttribute("repairsDetails", repairsDetails);
        System.out.println();
        return "details";
    }
}