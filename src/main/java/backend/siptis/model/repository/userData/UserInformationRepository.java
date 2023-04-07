package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {


    Optional<UserInformation> existsByCi(String ci);

    Optional<UserInformation> existsByCodSIS(String codSIS);
}
