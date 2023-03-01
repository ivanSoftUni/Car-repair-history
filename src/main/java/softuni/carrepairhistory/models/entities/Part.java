package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Car> cars;
    @ManyToOne
    private PartsCategory categoryName;

    public Part() {
        this.cars = new ArrayList<>();
    }


    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = Collections.unmodifiableList(cars);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PartsCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(PartsCategory categoryName) {
        this.categoryName = categoryName;
    }
}
