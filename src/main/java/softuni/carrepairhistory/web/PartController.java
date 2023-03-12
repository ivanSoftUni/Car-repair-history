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
import softuni.carrepairhistory.models.dto.RegisterPartCategoryDto;
import softuni.carrepairhistory.models.dto.RegisterPartDto;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.repositories.PartRepository;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;
import softuni.carrepairhistory.services.PartsCategoryService;
import softuni.carrepairhistory.services.PartsService;

import java.util.List;

@Controller
public class PartController {

    private final PartRepository partRepository;
    private final PartsCategoryRepository categoryRepository;
    private final PartsCategoryService partsCategoryService;
    private final PartsService partsService;

    @Autowired
    public PartController(PartRepository partRepository, PartsCategoryRepository categoryRepository, PartsCategoryService partsCategoryService, PartsService partsService) {
        this.partRepository = partRepository;
        this.categoryRepository = categoryRepository;
        this.partsCategoryService = partsCategoryService;
        this.partsService = partsService;
    }

    @ModelAttribute("registerPartDto")
    public RegisterPartDto init() {
        return new RegisterPartDto();
    }

    @ModelAttribute("registerPartCategoryDto")
    public RegisterPartCategoryDto initCategory() {
        return new RegisterPartCategoryDto();
    }

    @GetMapping("/category/add")
    public String category() {
        return "add-parts-category";
    }

    @PostMapping("/category/add")
    public String registerCategory(@Valid RegisterPartCategoryDto registerPartCategoryDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.partsCategoryService.createCategory(registerPartCategoryDto)) {
            redirectAttributes.addFlashAttribute("registerPartCategoryDto", registerPartCategoryDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerPartCategoryDto", bindingResult);

            return "redirect:/category/add";
        }


        return "redirect:/";
    }

    @GetMapping("/part/add")
    public String getPart(Model model) {

        List<PartsCategory> categoryList = this.categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        return "add-parts";
    }

    @PostMapping("/part/add")
    public String registerParts(@Valid RegisterPartDto registerPartDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerPartDto", registerPartDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerPartDto", bindingResult);

            return "redirect:/part/add";
        }

        this.partsService.createPart(registerPartDto);

        return "redirect:/";
    }


}
