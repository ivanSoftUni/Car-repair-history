package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "repairs")
public class Repair extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDate date;

    @Column
    private String comment;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne()
    private Car car;
    @OneToOne
    private UserEntity user;
    @OneToOne
    private VehiclesRepairsShop repairsShop;

    public Repair() {

    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public VehiclesRepairsShop getRepairsShop() {
        return repairsShop;
    }

    public void setRepairsShop(VehiclesRepairsShop repairsShop) {
        this.repairsShop = repairsShop;
    }
}
