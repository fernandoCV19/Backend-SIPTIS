package backend.siptis.model.repository.general;

import backend.siptis.model.entity.datosUsuario.UserCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCareerRepository extends JpaRepository<UserCareer, Integer > {
}
