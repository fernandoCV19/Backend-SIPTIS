package backend.siptis.auth.repository;

import backend.siptis.model.entity.datosUsuario.InformacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacionUsuarioRepository extends JpaRepository<InformacionUsuario, Integer> {

}
