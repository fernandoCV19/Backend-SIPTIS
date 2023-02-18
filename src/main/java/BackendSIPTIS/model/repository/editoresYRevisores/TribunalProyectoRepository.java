package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.TribunalProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TribunalProyectoRepository extends JpaRepository<TribunalProyecto, Long> {
}
