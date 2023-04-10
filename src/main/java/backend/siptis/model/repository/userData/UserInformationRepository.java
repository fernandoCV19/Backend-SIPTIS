package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.UserInformation;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {


    boolean existsByCi(String ci);

    boolean existsByCodSIS(String codSIS);



}
