package backend.siptis.model.repository.general;

import backend.siptis.model.entity.projectManagement.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Integer > {
    @Override
    List<Area> findAll();
}
