package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.dto.RegisterPartDto;
import softuni.carrepairhistory.models.entities.Part;
import softuni.carrepairhistory.models.entities.PartsCategory;
import softuni.carrepairhistory.repositories.PartRepository;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;

import java.util.Optional;

@Service
public class PartsService {

    private final PartRepository partRepository;
    private final PartsCategoryRepository partsCategoryRepository;

    @Autowired
    public PartsService(PartRepository partRepository, PartsCategoryRepository partsCategoryRepository) {
        this.partRepository = partRepository;
        this.partsCategoryRepository = partsCategoryRepository;
    }


    public void createPart(RegisterPartDto registerPartDto) {

        Optional<PartsCategory> partsCategory = this.partsCategoryRepository.findByName(registerPartDto.getCategory());

        Optional<Part> existPart = this.partRepository.findByName(registerPartDto.getName());

        if (existPart.isPresent() || partsCategory.isEmpty()) {
            return;
        }

        Part part = new Part();
        part.setName(registerPartDto.getName());
        part.setPartsCategory(partsCategory.get());

        partRepository.save(part);
    }

    public void initBasicPart() {
        Part part = new Part();
        part.setName("Биалетка");

        Optional<PartsCategory> partsCategory = this.partsCategoryRepository.findByName("Ходова част");

        part.setPartsCategory(partsCategory.get());

        this.partRepository.save(part);
    }
}
