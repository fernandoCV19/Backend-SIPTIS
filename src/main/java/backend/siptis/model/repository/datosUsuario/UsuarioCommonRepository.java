package backend.siptis.model.repository.datosUsuario;

import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCommonRepository extends JpaRepository<SiptisUser, Long> {
}