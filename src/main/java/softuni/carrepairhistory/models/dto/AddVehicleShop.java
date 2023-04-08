package softuni.carrepairhistory.models.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import softuni.carrepairhistory.models.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class AddVehicleShop {

    @NotNull
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    @Size(min = 3)
    private String city;

    @Size(max = 30)
    private String address;

    @NotNull
    @Pattern(regexp = "08[7-9][0-9]{7}")
    private String phoneNumber;

    public AddVehicleShop() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
