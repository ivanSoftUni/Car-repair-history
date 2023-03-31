package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.EditRepairDto;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.repositories.*;
import softuni.carrepairhistory.services.RepairService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RepairController {

    private final RepairRepository repairRepository;
    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final VehiclesRepairsShopRepository vehiclesRepairsShopRepository;
    private final RepairService repairService;

    @Autowired
    public RepairController(RepairRepository repairRepository,
                            CarRepository carRepository,
                            VehiclesRepairsShopRepository repairsShopRepository,
                            UserRepository userRepository,
                            RepairService repairService) {
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
        this.vehiclesRepairsShopRepository = repairsShopRepository;
        this.userRepository = userRepository;
        this.repairService = repairService;
    }

    @ModelAttribute("addRepairDto")
    public AddRepairDto initForm() {
        return new AddRepairDto();
    }

    @ModelAttribute("editRepairDto")
    public EditRepairDto initEditForm() {
        return new EditRepairDto();
    }


    @GetMapping("/repair/add")
    public String getRepair(Model model, Principal principal) {

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

        return "redirect:/users/all/cars";
    }

    @GetMapping("/repair/details")
    public String getDetails(Model model, Principal authenticatedPrincipal) {

        Optional<UserEntity> user = userRepository.findByUsername(authenticatedPrincipal.getName());

        List<RepairDetailDto> repairsDetails = this.repairRepository.findAllByUserId(user.get().getId())
                .stream()
                .map(repairService::mapToRepairDetail)
                .toList();

        model.addAttribute("repairsDetails", repairsDetails);

        return "repair-details";
    }

    @GetMapping("/remove/{id}")
    public String removeRepair(@PathVariable Long id) {

        this.repairService.removeRepair(id);

        return "redirect:/repair/details";
    }

    @PostMapping("/repair/edit/{id}")
    public String editRepair(@PathVariable Long id, Model model) {

        Repair repair = this.repairService.getRepair(id);
        model.addAttribute("repair", repair);


        return "redirect:/repair/details";
    }

    @GetMapping("/repair/edit/{id}")
    public String updateRepair(@PathVariable Long id, Model model) {

        model.addAttribute("editRepairDto", this.repairService.getRepair(id));
        return "edit-repair";
    }
}