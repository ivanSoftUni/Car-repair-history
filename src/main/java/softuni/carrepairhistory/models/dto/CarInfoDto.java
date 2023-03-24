package softuni.carrepairhistory.models.dto;

import java.math.BigDecimal;

public class CarInfoDto {
    private Long id;
    private String brand;
    private String model;
    private String registerNumber;
    private long repairsCount;

    private BigDecimal totalRepairPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public long getRepairsCount() {
        return repairsCount;
    }

    public void setRepairsCount(long repairsCount) {
        this.repairsCount = repairsCount;
    }

    public BigDecimal getTotalRepairPrice() {
        return totalRepairPrice;
    }

    public void setTotalRepairPrice(BigDecimal totalRepairPrice) {
        this.totalRepairPrice = totalRepairPrice;
    }
}
