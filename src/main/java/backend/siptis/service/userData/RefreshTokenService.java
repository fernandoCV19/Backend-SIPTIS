package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken createRefreshToken(SiptisUser user);

    RefreshToken verifyExpirationDate(RefreshToken refreshToken);


    ServiceAnswer getToken(Long id);

    int deleteToken(Long userId);
}
