package softuni.carrepairhistory.web;


import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.models.entities.Repair;
import softuni.carrepairhistory.models.exception.ObjectNotFoundException;
import softuni.carrepairhistory.repositories.RepairRepository;
import softuni.carrepairhistory.services.RepairService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repair")
public class RestControllerAllRepairs {

    private final RepairService repairService;
    private final RepairRepository repairRepository;

    @Autowired
    public RestControllerAllRepairs(RepairService repairService, RepairRepository repairRepository) {
        this.repairService = repairService;
        this.repairRepository = repairRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RepairDetailDto>> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RepairDetailDto> getRepairById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(repairService.getRepairDetail(id));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ObjectNotFoundException> repairNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
