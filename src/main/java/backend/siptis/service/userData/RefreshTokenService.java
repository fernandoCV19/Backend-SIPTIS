package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);

    RefreshToken verifyExpirationDate(RefreshToken refreshToken);

    int deleteToken(Long userId);
}
