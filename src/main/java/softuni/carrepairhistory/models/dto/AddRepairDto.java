package softuni.carrepairhistory.models.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddRepairDto {

    @NotNull
    private Long carId;
    @NotEmpty
    @Size(min = 5, max = 30)
    private String description;
    @NotNull
    @PastOrPresent()
    private LocalDate date;

    private String comment;
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Long vehicleShopId;

    public AddRepairDto() {

    }

    public Long getVehicleShopId() {
        return vehicleShopId;
    }

    public void setVehicleShopId(Long vehicleShopId) {
        this.vehicleShopId = vehicleShopId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

}
