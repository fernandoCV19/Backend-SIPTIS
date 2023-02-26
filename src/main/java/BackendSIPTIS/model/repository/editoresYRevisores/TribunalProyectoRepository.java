package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.TribunalProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TribunalProyectoRepository extends JpaRepository<TribunalProyecto, Long> {


    List<TribunalProyecto> findByUsuario_idAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<TribunalProyecto> findByUsuario_idAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<TribunalProyecto> findByUsuario_idAndAceptadoIsTrueAndNotaDefensaIsNull(Long id);

    List<TribunalProyecto> findByUsuario_idAndNotaDefensaIsNotNull(Long id);
}
