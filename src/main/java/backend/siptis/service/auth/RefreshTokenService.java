package backend.siptis.service.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.service.user_data.UserInformationService;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken createRefreshToken(SiptisUser user);

    boolean verifyValidExpirationDate(RefreshToken refreshToken);

}
