package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;
import softuni.carrepairhistory.models.enums.UserRoleEnum;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRoleEntity() {

    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

}
