package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.AddRepairDto;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;
import softuni.carrepairhistory.models.exception.ObjectNotFoundException;
import softuni.carrepairhistory.repositories.*;
import softuni.carrepairhistory.services.RepairService;

import java.security.Principal;
import java.util.List;

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
    public String getRepairDetails(Model model, Principal authenticatedPrincipal) {

        UserEntity user = userRepository.findByUsername(authenticatedPrincipal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + authenticatedPrincipal.getName() + " not found."));

        List<RepairDetailDto> repairsDetails = this.repairRepository.findAllByUserIdOrderByDateDesc(user.getId())
                .stream()
                .map(repairService::mapToRepairDetail)
                .toList();

        model.addAttribute("repairsDetails", repairsDetails);

        return "repair-details";
    }

    @GetMapping("/remove/{id}")
    public String removeRepair(@PathVariable Long id) {

        try {

            this.repairService.removeRepair(id);

        } catch (Exception e) {

            throw new ObjectNotFoundException(id, "Ремонт");
        }

        return "redirect:/repair/details";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView onRepairNotFound(ObjectNotFoundException notFoundException) {
        ModelAndView modelAndView = new ModelAndView("not-found-error");

        modelAndView.addObject("objectId", notFoundException.getObjectId());
        modelAndView.addObject("objectType", notFoundException.getObjectType());

        return modelAndView;
    }
}