package backend.siptis.model.repository.user_data;

import backend.siptis.model.entity.user_data.UserCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCareerRepository extends JpaRepository<UserCareer, Integer> {

    @Override
    List<UserCareer> findAll();

    boolean existsByName(String name);

    UserCareer findUserCareerByName(String name);
}
