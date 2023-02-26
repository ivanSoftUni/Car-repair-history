package softuni.carrepairhistory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {


    @GetMapping("/car/add")
    public String addCar() {

        return "add-car";
    }
}
