package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;
import softuni.carrepairhistory.models.enums.CarFuels;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "made_year", nullable = false)
    private Integer madeYear;

    @Column(name = "horse_power", nullable = false)
    private int horsePower;

    @Column
    private String engine;

    @Column
    @Enumerated(EnumType.STRING)
    private CarFuels fuel;

    @Column(name = "register_number", nullable = false, unique = true)
    private String registerNumber;

    @ManyToOne
    private UserEntity userEntity;
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Repair> repairList = new ArrayList<>();

    public Car() {

    }

    public String getEngine() {
        return engine;
    }

//    public List<Repair> getRepairList() {
//        return repairList;
//    }
//
//    public void setRepairList(List<Repair> repairList) {
//        this.repairList = repairList;
//    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public CarFuels getFuel() {
        return fuel;
    }

    public void setFuel(CarFuels fuel) {
        this.fuel = fuel;
    }
}
