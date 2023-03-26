package backend.siptis.model.repository.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCommonRepository extends JpaRepository<Usuario, Long> {
}