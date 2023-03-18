package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.TribunalProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TribunalProyectoRepository extends JpaRepository<TribunalProyecto, Long> {


    List<TribunalProyecto> findByTribunalAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<TribunalProyecto> findByTribunalAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<TribunalProyecto> findByTribunalAndAceptadoIsTrueAndNotaDefensaIsNull(Long id);

    List<TribunalProyecto> findByTribunalAndNotaDefensaIsNotNull(Long id);
}
