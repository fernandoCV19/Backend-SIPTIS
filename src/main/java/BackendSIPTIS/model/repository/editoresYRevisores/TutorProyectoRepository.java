package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.TutorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorProyectoRepository extends JpaRepository<TutorProyecto, Long> {
}
