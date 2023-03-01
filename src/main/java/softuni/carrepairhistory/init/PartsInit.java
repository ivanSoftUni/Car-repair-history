package softuni.carrepairhistory.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.carrepairhistory.repositories.PartRepository;
import softuni.carrepairhistory.repositories.PartsCategoryRepository;
import softuni.carrepairhistory.services.PartsCategoryService;
import softuni.carrepairhistory.services.PartsService;

@Component
public class PartsInit implements CommandLineRunner {

    private final PartsCategoryService partsCategoryService;
    private final PartsService partsService;
    private final PartsCategoryRepository partsCategoryRepository;

    private final PartRepository partRepository;

    @Autowired
    public PartsInit(PartsCategoryService partsCategoryService, PartsService partsService, PartsCategoryRepository partsCategoryRepository, PartRepository partRepository) {
        this.partsCategoryService = partsCategoryService;
        this.partsService = partsService;
        this.partsCategoryRepository = partsCategoryRepository;
        this.partRepository = partRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (partsCategoryRepository.count() == 0 && partRepository.count() == 0) {
            this.partsCategoryService.initBasicCategory();
            this.partsService.initBasicPart();
        }

    }
}
