package softuni.carrepairhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carrepairhistory.models.entities.RoleEntity;
import softuni.carrepairhistory.models.enums.UserRoleEnum;
import softuni.carrepairhistory.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initAdminRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(UserRoleEnum.ADMIN);

        this.roleRepository.saveAndFlush(roleEntity);
    }

    public void initUserRole() {
        RoleEntity roleEntity = new RoleEntity().setRole(UserRoleEnum.USER);

        this.roleRepository.save(roleEntity);
    }
}
