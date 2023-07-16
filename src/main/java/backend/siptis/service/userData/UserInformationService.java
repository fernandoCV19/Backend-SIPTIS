package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserInformation;

public interface UserInformationService {

    boolean existByCodSIS(String codSIS);

    boolean existByCi(String ci);

    boolean existsById(Long id);

    UserInformation findById(Long id);





}
