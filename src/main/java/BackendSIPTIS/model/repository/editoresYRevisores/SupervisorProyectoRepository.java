package BackendSIPTIS.model.repository.editoresYRevisores;

import BackendSIPTIS.model.entity.editoresYRevisores.DocenteTG2Proyecto;
import BackendSIPTIS.model.entity.editoresYRevisores.SupervisorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisorProyectoRepository extends JpaRepository<SupervisorProyecto, Long> {

    List<DocenteTG2Proyecto> findByUsuario_idAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<DocenteTG2Proyecto> findByUsuario_idAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<DocenteTG2Proyecto> findByUsuario_idAndAceptadoIsTrue(Long id);
}
