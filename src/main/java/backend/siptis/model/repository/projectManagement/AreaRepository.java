package backend.siptis.model.repository.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Area;
import backend.siptis.model.entity.userData.UserArea;
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
