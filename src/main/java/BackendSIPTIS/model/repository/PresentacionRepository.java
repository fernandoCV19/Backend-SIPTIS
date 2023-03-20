package BackendSIPTIS.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSIPTIS.model.entity.gestionProyecto.Presentacion;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
    Presentacion findById(int id);
    Optional<Presentacion> findTopByProyectoGradoIdAndEntregado(long idProyecto , boolean entregado);

    @Override
    void deleteById(Long aLong);
}
