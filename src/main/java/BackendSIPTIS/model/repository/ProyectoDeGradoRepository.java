package BackendSIPTIS.model.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import BackendSIPTIS.model.entity.gestionProyecto.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;

@Repository
public interface ProyectoDeGradoRepository extends JpaRepository<ProyectoGrado, Long> {
    Optional<ProyectoGrado> findById(int id);

}