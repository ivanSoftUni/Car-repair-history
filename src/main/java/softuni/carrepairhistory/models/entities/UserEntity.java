package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> userRoles = new ArrayList<>();

    public UserEntity() {

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
