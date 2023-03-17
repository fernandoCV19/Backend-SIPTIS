package BackendSIPTIS.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;

@Repository
public interface ProyectoDeGradoRepository extends JpaRepository<ProyectoGrado, Long> {
    ProyectoGrado findById(int id);
    List <ProyectoGrado> findAll();
    //void deleteById(int id);

    
}