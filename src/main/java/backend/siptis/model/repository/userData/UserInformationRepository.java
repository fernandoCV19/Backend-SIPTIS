package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

}
