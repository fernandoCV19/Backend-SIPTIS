package BackendSIPTIS.model.repository.gestionProyecto;

import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoGradoRepository extends JpaRepository<ProyectoGrado, Long> {
}
