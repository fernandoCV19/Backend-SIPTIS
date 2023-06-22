package backend.siptis.model.repository.general;

import backend.siptis.model.entity.userData.UserArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAreaRepository extends JpaRepository<UserArea, Integer > {

    @Override
    List<UserArea> findAll();
}
