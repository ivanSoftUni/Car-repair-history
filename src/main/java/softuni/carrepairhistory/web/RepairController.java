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
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.repositories.CarRepository;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.repositories.VehiclesRepairsShopRepository;

import java.util.List;

@Controller
public class RepairController {

    private final PartsCategoryRepository categoryRepository;
    private final RepairRepository repairRepository;
    private final CarRepository carRepository;
    private final VehiclesRepairsShopRepository repairsShopRepository;

    @Autowired
    public RepairController(PartsCategoryRepository categoryRepository, RepairRepository repairRepository, CarRepository carRepository, VehiclesRepairsShopRepository repairsShopRepository) {
        this.categoryRepository = categoryRepository;
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;

        this.repairsShopRepository = repairsShopRepository;
    }

    @ModelAttribute("addRepairDto")
    public AddRepairDto initForm() {
        return new AddRepairDto();
    }

    @GetMapping("/repair/add")
    public String getRepair(Model model) {

        List<PartsCategory> categoryList = this.categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        List<Car> cars = this.carRepository.findAll();
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

        return "redirect:/home";
    }
}
