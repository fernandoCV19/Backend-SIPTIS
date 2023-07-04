package backend.siptis.model.repository.general;

import backend.siptis.model.entity.userData.UserCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCareerRepository extends JpaRepository<UserCareer, Integer > {

    @Override
    List<UserCareer> findAll();
}
