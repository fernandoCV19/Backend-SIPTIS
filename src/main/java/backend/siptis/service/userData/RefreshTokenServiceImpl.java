package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;


public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Override
    public RefreshToken findByToken(String token) {
        return null;
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        return null;
    }

    @Override
    public boolean verifyExpirationDate(RefreshToken refreshToken) {
        return false;
    }
}
