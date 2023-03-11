package backend.siptis.auth.repository;

import backend.siptis.auth.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}
