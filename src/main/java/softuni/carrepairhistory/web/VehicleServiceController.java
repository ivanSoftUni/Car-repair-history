package softuni.carrepairhistory.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.carrepairhistory.models.dto.AddVehicleShop;
import softuni.carrepairhistory.services.VehicleShopService;

import java.security.Principal;

@Controller
public class VehicleServiceController {

    private final VehicleShopService vehicleShopService;

    @Autowired
    public VehicleServiceController(VehicleShopService vehicleShopService) {
        this.vehicleShopService = vehicleShopService;
    }

    @ModelAttribute("addVehicleShop")
    public AddVehicleShop initVehicleShop() {
        return new AddVehicleShop();
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

        return "redirect:/users/all/cars";
    }
}
