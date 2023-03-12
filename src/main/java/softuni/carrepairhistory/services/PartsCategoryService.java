package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.RegisterPartCategoryDto;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;

import java.util.Optional;

@Service
public class PartsCategoryService {

    private final PartsCategoryRepository partsCategoryRepository;

    @Autowired
    public PartsCategoryService(PartsCategoryRepository partsCategoryRepository) {
        this.partsCategoryRepository = partsCategoryRepository;
    }

    public boolean createCategory(RegisterPartCategoryDto registerPartCategoryDto) {

        Optional<PartsCategory> existCategory = this.partsCategoryRepository.findByName(registerPartCategoryDto.getName());

        if (existCategory.isPresent()) {
            return false;
        }

        PartsCategory partsCategory = new PartsCategory();
        partsCategory.setName(registerPartCategoryDto.getName());

        this.partsCategoryRepository.save(partsCategory);

        return true;
    }


    public void initBasicCategory() {
        PartsCategory partsCategory = new PartsCategory();
        partsCategory.setName("Ходова част");
        this.partsCategoryRepository.save(partsCategory);
    }

}
