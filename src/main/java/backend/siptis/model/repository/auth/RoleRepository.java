package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * " +
            "FROM role_ role " +
            "WHERE role.name_ NOT LIKE 'ADMIN' AND role.name_ " +
            " NOT IN ('STUDENT', 'ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR') ", nativeQuery = true)
    List<Role> getAllowedRoles();

    Role findRoleByName(String name);

    Role findRoleById(Long id);

    boolean existsRoleByName(String name);

    boolean existsRoleById(Long id);


}
