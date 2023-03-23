package softuni.carrepairhistory.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RepairDetailDto {
    private Long id;
    private String repairTitle;

    private String carInfo;
    private BigDecimal price;
    private LocalDate date;
    private String comment;

    private String serviceShopName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepairTitle() {
        return repairTitle;
    }

    public void setRepairTitle(String repairTitle) {
        this.repairTitle = repairTitle;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getServiceShopName() {
        return serviceShopName;
    }

    public void setServiceShopName(String serviceShopName) {
        this.serviceShopName = serviceShopName;
    }
}
