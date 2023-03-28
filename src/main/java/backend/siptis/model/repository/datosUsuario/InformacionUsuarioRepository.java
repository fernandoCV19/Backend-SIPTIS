package backend.siptis.model.repository.datosUsuario;

import backend.siptis.model.entity.datosUsuario.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacionUsuarioRepository extends JpaRepository<UserInformation, Integer> {

}
