package backend.siptis.model.repository.general;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
