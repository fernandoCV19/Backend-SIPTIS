package backend.siptis.model.repository;

import java.util.Optional;

import backend.siptis.model.entity.gestionProyecto.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
    Presentacion findById(int id);
    Optional<Presentacion> findTopByProyectoGradoIdAndEntregado(long idProyecto , boolean entregado);

    @Override
    void deleteById(Long aLong);
}
