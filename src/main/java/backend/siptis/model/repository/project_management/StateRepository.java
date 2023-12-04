package backend.siptis.model.repository.project_management;
import backend.siptis.model.entity.project_management.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByName(String name);
}
