package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken createRefreshToken(SiptisUser user);

    RefreshToken verifyExpirationDate(RefreshToken refreshToken);



    int deleteToken(Long userId);
}
