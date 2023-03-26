package backend.siptis.model.repository.editoresYRevisores;

import backend.siptis.model.entity.editoresYRevisores.DocenteTG2Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteTG2ProyectoRepository extends JpaRepository<DocenteTG2Proyecto, Long> {

    List<DocenteTG2Proyecto> findByDocenteAndAceptadoIsFalseAndRevisadoIsTrue(Long id);

    List<DocenteTG2Proyecto> findByDocenteAndAceptadoIsFalseAndRevisadoIsFalse(Long id);

    List<DocenteTG2Proyecto> findByDocenteAndAceptadoIsTrue(Long id);
}
