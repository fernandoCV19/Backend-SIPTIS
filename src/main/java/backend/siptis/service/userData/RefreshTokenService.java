package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken createRefreshToken(SiptisUser user);

    boolean verifyValidExpirationDate(RefreshToken refreshToken);

}
