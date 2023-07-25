package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.commons.ServiceAnswer;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(Long userId);

    boolean verifyExpirationDate(RefreshToken refreshToken);
}
