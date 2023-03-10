package softuni.carrepairhistory.models.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCarDto {

    @NotNull
    @Size(min = 3)
    private String brand;

    @NotNull
    private String model;

    @NotNull
    @Size(min = 2)
    private String madeYear;

    @NotNull
    @Positive
    private int horsePower;

    @NotNull
    private String engine;

    @NotNull
    private String fuel;

@NotNull
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

    public String getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(String madeYear) {
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
