package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.service.auth.RefreshTokenService;
import backend.siptis.service.user_data.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceLogIn {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;

    public ServiceAnswer logIn(LogInDTO logInDTO) {
        if (!siptisUserServiceExistValidation.existsUserByEmail(logInDTO.getEmail()))
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, null);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInDTO.getEmail(), logInDTO.getPassword()));
            if (auth.isAuthenticated()) {
                UserInformationService.UserDetailImp userDetails = (UserInformationService.UserDetailImp) auth.getPrincipal();
                TokenDTO tokenDTO = createTokenDTO(userDetails);
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(tokenDTO).build();
            } else {
                return createResponse(ServiceMessage.ERROR_LOG_IN, null);
            }
        } catch (Exception e) {
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, null);
        }
    }

    private TokenDTO createTokenDTO(UserInformationService.UserDetailImp userDetails) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails);
        String token = JWTokenUtils.createToken(userDetails);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setRefreshToken(refreshToken.getToken());
        return tokenDTO;
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}