package BackendSIPTIS.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSIPTIS.model.entity.gestionProyecto.Presentacion;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
    Presentacion findById(int id);
    List <Presentacion> findAll();
    //void deleteById(int id);

    
}
