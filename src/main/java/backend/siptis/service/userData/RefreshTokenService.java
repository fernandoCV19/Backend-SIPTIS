package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken createRefreshToken(SiptisUser user);

    RefreshToken verifyExpirationDate(RefreshToken refreshToken);

    ServiceAnswer getAllToken();

    ServiceAnswer getToken(Long id);

    ServiceAnswer getToken(String token);

    Long getTokenUser(String token);


    int deleteToken(Long userId);
}
