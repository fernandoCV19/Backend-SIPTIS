package backend.siptis.service.auth.siptisUserServices;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTokenOperationsTest {
    @Autowired
    private SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2OTgzMjcyMzgsImlkIjoxLCJwcm9qZWN0cyI6W10sInJvbGVzIjoiW0FETUlOXSJ9.X0DQBTIXmRUJipiRzLg3Gs9DfiVUcGibOX2K04k3ry7Clfl2KWk87fCF3KtQd7Zx\n";
    @Test
    @DisplayName("test get id from token")
    public void givenExpiredTokenWhenGetIdFromTokenThenExceptio(){
        try{
            assertNull(siptisUserServiceTokenOperations.getIdFromToken(token));
        }catch (Exception e){
        }
    }
    @Test
    @DisplayName("test get projects from token")
    public void givenExpiredTokenWhenGetProjectsFromTokenThenException(){
        try{
            assertNull(siptisUserServiceTokenOperations.getProjectsFromToken(token));
        }catch (Exception e){ }
    }

}
