package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> userRoles = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<Repair> repairs = new ArrayList<>();

    public UserEntity() {

    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(List<RoleEntity> roles) {
        this.userRoles = roles;
        return this;
    }

    public UserEntity addRole(RoleEntity role) {
        this.userRoles.add(role);
        return this;
    }
}
