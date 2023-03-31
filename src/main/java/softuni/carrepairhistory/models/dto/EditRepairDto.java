package softuni.carrepairhistory.models.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import softuni.carrepairhistory.models.entities.Car;
import softuni.carrepairhistory.models.entities.UserEntity;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditRepairDto {
    @NotNull
    private String description;

    @NotNull
    @PastOrPresent()
    private LocalDate date;

    @NotNull
    private String comment;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Car car;
    @NotNull
    private VehiclesRepairsShop repairsShop;

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
