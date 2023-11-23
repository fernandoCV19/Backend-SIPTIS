package backend.siptis.model.repository.user_data;

import backend.siptis.model.entity.user_data.UserArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAreaRepository extends JpaRepository<UserArea, Integer> {

    @Override
    List<UserArea> findAll();

    boolean existsUserAreaById(Long id);

    boolean existsUserAreaByName(String name);
}
