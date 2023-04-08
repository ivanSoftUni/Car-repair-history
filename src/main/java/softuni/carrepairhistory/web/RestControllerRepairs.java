package softuni.carrepairhistory.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softuni.carrepairhistory.models.dto.RepairDetailDto;
import softuni.carrepairhistory.services.RepairService;

import java.util.List;

@RestController
@RequestMapping("/api/repair")
public class RestControllerRepairs {

    private final RepairService repairService;

    @Autowired
    public RestControllerRepairs(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RepairDetailDto>> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RepairDetailDto> getRepairById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(repairService.getRepairDetail(id));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> repairNotFound() {
        return new ResponseEntity<>("ERROR: Търсеният от Вас ремонт НЕ съществува", HttpStatus.NOT_FOUND);
    }
}
