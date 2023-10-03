package backend.siptis.model.repository.general;

import backend.siptis.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


    Role findRoleByName(String name);

    boolean existsRoleByName(String name);
}
