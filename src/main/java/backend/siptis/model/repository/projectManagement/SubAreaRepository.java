package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubAreaRepository extends JpaRepository<SubArea, Integer> {
    @Override
    List<SubArea> findAll();

    boolean existsSubAreaById(Long id);
    boolean existsSubAreaByName(String name);

    Optional<SubArea> findById(Long id);
}
