package softuni.carrepairhistory.models.dto;


import jakarta.validation.constraints.*;

public class CreateCarDto {

    @NotNull
    @Size(min = 3)
    private String brand;

    @NotEmpty
    @Size(min = 2)
    private String model;

    @NotNull
    @Min(2)
    @Positive
    private Integer madeYear;

    @NotNull
    @Positive
    private int horsePower;

    @NotEmpty
    @Size(min = 3, max = 4)
    private String engine;

    @NotEmpty
    private String fuel;

    @NotNull
    @Size(min = 4, max = 10)
    private String registerNumber;


    public CreateCarDto() {

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

    public Integer getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(Integer madeYear) {
        this.madeYear = madeYear;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
}
