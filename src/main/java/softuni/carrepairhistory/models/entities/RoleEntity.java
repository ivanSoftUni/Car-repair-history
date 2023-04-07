package softuni.carrepairhistory.models.entities;

import jakarta.persistence.*;
import softuni.carrepairhistory.models.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public RoleEntity(UserRoleEnum user) {

    }

    public RoleEntity() {

    }

    public UserRoleEnum getName() {
        return name;
    }

    public RoleEntity setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }

}
