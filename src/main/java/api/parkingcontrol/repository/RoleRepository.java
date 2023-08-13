package api.parkingcontrol.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import api.parkingcontrol.enums.RoleName;
import api.parkingcontrol.models.RoleModel;


//Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID>{
    boolean existsByRoleName(RoleName roleName);
   
}
