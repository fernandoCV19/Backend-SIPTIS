package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.RefreshTokenService;
import backend.siptis.service.user_data.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class SiptisUserServiceTokenOperations {

    private final RefreshTokenService refreshTokenService;
    private final SiptisUserRepository siptisUserRepository;

    public Long getIdFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWTokenUtils.getId(token);
    }

    public List<?> getProjectsFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWTokenUtils.getProjects(token);
    }

    public ServiceAnswer updateToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken);
        if (token == null) {
            return createResponse(ServiceMessage.NOT_FOUND, null);
        }
        if (!refreshTokenService.verifyValidExpirationDate(token)) {
            return createResponse(ServiceMessage.EXPIRED_REFRESH_TOKEN, null);
        }
        SiptisUser user = token.getSiptisUser();
        String updatedToken = JWTokenUtils.createToken(new UserInformationService.UserDetailImp(user));
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(updatedToken);
        tokenDTO.setRefreshToken(newRefreshToken.getToken());

        return createResponse(ServiceMessage.OK, tokenDTO);
    }

    public ServiceAnswer existsTokenPassword(String tokenPassword) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.existsByTokenPassword(tokenPassword)).build();
    }

    public SiptisUser findByTokenPassword(String tokenPassword) {
        Optional<SiptisUser> oUser = siptisUserRepository.findByTokenPassword(tokenPassword);
        if (oUser.isEmpty())
            return null;
        return oUser.get();
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
