package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;
import softuni.carrepairhistory.models.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public RoleEntity(UserRoleEnum user) {

    }

    public RoleEntity() {

    }

    public UserRoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

}
