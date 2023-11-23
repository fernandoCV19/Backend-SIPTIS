package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceTokenOperationsTest {
    @Autowired
    private SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2OTgzMjcyMzgsImlkIjoxLCJwcm9qZWN0cyI6W10sInJvbGVzIjoiW0FETUlOXSJ9.X0DQBTIXmRUJipiRzLg3Gs9DfiVUcGibOX2K04k3ry7Clfl2KWk87fCF3KtQd7Zx\n";

    @Test
    @DisplayName("test get id from token")
    void givenExpiredTokenWhenGetIdFromTokenThenException() {
        try {
            assertNull(siptisUserServiceTokenOperations.getIdFromToken(token));
        } catch (Exception e) {
        }
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
    @DisplayName("test update expired token")
    void givenBadRefreshTokenWhenUpdateTokenThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceTokenOperations.updateToken("123").getServiceMessage());
    }

    @Test
    @DisplayName("test verify if exist token password")
    void givenBadTokenPasswordWhenExistsTokenPasswordThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceTokenOperations.existsTokenPassword("123").getServiceMessage());
    }

    @Test
    @DisplayName("test find by token password")
    void givenBadTokenPasswordWhenFindByTokenPasswordThenServiceMessageOK() {
        assertNull(siptisUserServiceTokenOperations.findByTokenPassword("123"));
    }


}
