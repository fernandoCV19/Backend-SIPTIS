package backend.siptis.model.repository.gestionProyecto;

import java.util.Optional;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoDeGradoRepository extends JpaRepository<ProyectoGrado, Long> {
    Optional<ProyectoGrado> findById(int id);

}