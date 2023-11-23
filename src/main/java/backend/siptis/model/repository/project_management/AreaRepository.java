package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Integer> {
    @Override
    List<Area> findAll();

    boolean existsAreaById(Long id);

    boolean existsAreaByName(String name);

    Optional<Area> findById(Long id);
}
