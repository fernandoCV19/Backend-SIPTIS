package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.userData.RefreshTokenRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final long REFRESH_TOKEN_EXPIRE_TIME_DURATION
                = 24 * 60 * 60 * 1000; //1 dia

    private final RefreshTokenRepository refreshTokenRepository;
    private final SiptisUserRepository siptisUserRepository;
    @Override
    public RefreshToken findByToken(String token) {

        if(! refreshTokenRepository.existsByToken(token)){
            return null;
        }
        return refreshTokenRepository.findByToken(token).get();
    }

    @Override
    public RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI) {
        SiptisUser user = siptisUserRepository.findById(userDI.getId()).get();
        RefreshToken token = new RefreshToken();
        if(refreshTokenRepository.existsBySiptisUser(user)){
            token = refreshTokenRepository.findBySiptisUser(user);
        }
        token.setSiptisUser(user);
        token.setExpireDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRE_TIME_DURATION));
        token.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(token);
        return token;
    }

    @Override
    public RefreshToken createRefreshToken(SiptisUser user) {
        RefreshToken token = new RefreshToken();
        if(refreshTokenRepository.existsBySiptisUser(user)){
            token = refreshTokenRepository.findBySiptisUser(user);
        }
        token.setSiptisUser(user);
        token.setExpireDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRE_TIME_DURATION));
        token.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(token);
        return token;
    }

    @Override
    public boolean verifyValidExpirationDate(RefreshToken refreshToken) {
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            return false;
        }
        return true;
    }

}
