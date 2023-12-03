package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserServiceTokenOperationsTest {
    private final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2OTgzMjcyMzgsImlkIjoxLCJwcm9qZWN0cyI6W10sInJvbGVzIjoiW0FETUlOXSJ9.X0DQBTIXmRUJipiRzLg3Gs9DfiVUcGibOX2K04k3ry7Clfl2KWk87fCF3KtQd7Zx\n";
    @Autowired
    private SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    @Autowired
    private SiptisUserServiceLogIn serviceLogin;
    private LogInDTO logInDTO;
    @Autowired
    private SiptisUserRepository siptisUserRepository;

    private TokenDTO logInUser() {
        logInDTO = new LogInDTO();
        logInDTO.setEmail("dilan.est@mail.com");
        logInDTO.setPassword("mavl");
        ServiceAnswer answer = serviceLogin.logIn(logInDTO);
        TokenDTO tokenDTO = (TokenDTO) answer.getData();
        return tokenDTO;
    }

    @Test
    @DisplayName("test get id from token")
    void givenExpiredTokenWhenGetIdFromTokenThenException() {
        try {
            assertNull(siptisUserServiceTokenOperations.getIdFromToken(token));
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test get id from token success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenExpiredTokenWhenGetIdFromTokenThenSuccess() {
        TokenDTO dto = logInUser();
        assertNotNull(siptisUserServiceTokenOperations.getIdFromToken(dto.getToken()));
    }

    @Test
    @DisplayName("test get projects from token")
    void givenExpiredTokenWhenGetProjectsFromTokenThenException() {
        try {
            assertNull(siptisUserServiceTokenOperations.getProjectsFromToken(token));
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test get projects from token success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenExpiredTokenWhenGetProjectsFromTokenFromTokenThenSuccess() {
        TokenDTO dto = logInUser();
        assertNotNull(siptisUserServiceTokenOperations.getProjectsFromToken(dto.getToken()));
    }

    @Test
    @DisplayName("test update expired token not found")
    void givenBadRefreshTokenWhenUpdateTokenThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceTokenOperations.updateToken("123").getServiceMessage());
    }

    @Test
    @DisplayName("test update expired token success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadRefreshTokenWhenUpdateTokenThenServiceMessage() {
        TokenDTO dto = logInUser();
        assertEquals(ServiceMessage.OK, siptisUserServiceTokenOperations.updateToken(dto.getRefreshToken()).getServiceMessage());
    }

    @Test
    @DisplayName("test verify if exist token password")
    void givenBadTokenPasswordWhenExistsTokenPasswordThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceTokenOperations.existsTokenPassword("123").getServiceMessage());
    }

    @Test
    @DisplayName("test find by token password null")
    void givenBadTokenPasswordWhenFindByTokenPasswordThenNull() {
        assertNull(siptisUserServiceTokenOperations.findByTokenPassword("123"));
    }

    @Test
    @DisplayName("test find by token password success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadTokenPasswordWhenFindByTokenPasswordRefreshToken() {
        SiptisUser user = new SiptisUser();
        user.setEmail("email");
        user.setPassword("pass");
        user.setTokenPassword("token");
        siptisUserRepository.save(user);
        assertNotNull(siptisUserServiceTokenOperations.findByTokenPassword("token"));
    }


}
