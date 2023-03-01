package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String manufacturer;

    @Column
    private BigDecimal price;

    @OneToMany
    private List<Car> cars;
    @ManyToOne
    private PartsCategory categoryName;

    public Part() {

    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PartsCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(PartsCategory categoryName) {
        this.categoryName = categoryName;
    }
}
