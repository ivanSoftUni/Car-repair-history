package softuni.carrepairhistory.models.dto;

import softuni.carrepairhistory.models.entities.RoleEntity;

import java.util.List;

public class UserStatusDto {

    private String username;

    private String email;

    private List<RoleEntity> roles;

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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RoleEntity role : roles) {
            sb.append(role.getRole().toString() + " ");

        }
        return sb.toString().trim();
    }
}
