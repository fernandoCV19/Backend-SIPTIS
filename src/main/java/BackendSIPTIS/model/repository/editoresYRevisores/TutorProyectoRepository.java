package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.TutorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorProyectoRepository extends JpaRepository<TutorProyecto, Long> {

    List<TutorProyecto> findByTutorAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<TutorProyecto> findByTutorAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<TutorProyecto> findByTutorAndAceptadoIsTrue(Long id);
}
