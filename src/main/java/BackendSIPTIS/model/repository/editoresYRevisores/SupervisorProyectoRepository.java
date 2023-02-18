package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.SupervisorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorProyectoRepository extends JpaRepository<SupervisorProyecto, Long> {
}
