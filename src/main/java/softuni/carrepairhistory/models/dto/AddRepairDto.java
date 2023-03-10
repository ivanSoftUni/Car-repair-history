package softuni.carrepairhistory.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import softuni.carrepairhistory.models.entities.Part;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AddRepairDto {

    @NotNull
    private Long carId;
    @NotNull
    private String description;
    @PastOrPresent
    private LocalDate date;

    private String comment;
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private VehiclesRepairsShop repairsShop;
    private List<Part> partList;


    public AddRepairDto() {

    }

    public VehiclesRepairsShop getRepairsShop() {
        return repairsShop;
    }

    public void setRepairsShop(VehiclesRepairsShop repairsShop) {
        this.repairsShop = repairsShop;
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

    public List<Part> getPartList() {
        return partList;
    }

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }
}
