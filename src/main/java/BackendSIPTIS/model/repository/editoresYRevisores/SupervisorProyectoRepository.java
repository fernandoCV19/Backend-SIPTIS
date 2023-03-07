package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.SupervisorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisorProyectoRepository extends JpaRepository<SupervisorProyecto, Long> {

    List<SupervisorProyecto> findBySupervisorAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<SupervisorProyecto> findBySupervisorAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<SupervisorProyecto> findBySupervisorAndAceptadoIsTrue(Long id);
}
