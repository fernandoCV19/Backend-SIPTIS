package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;
    private Document document;

    @BeforeEach
    public void createPlaceToDefense(){
        document = new Document();
        document.setPath("path");
        document.setSiptisUser(new SiptisUser());
        document.setDescription("document for test");
    }

    @Test
    @DisplayName("Test for find document by id")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDocumentId_whenFindById_thenDocumentObject(){
        assertFalse(documentRepository.findById(1L).isEmpty());
    }

    @Test
    @DisplayName("Test for find document by path")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDocumentPath_whenFindDocumentByPath_thenDocumentObject(){
        assertNotNull(documentRepository.findDocumentByPath("PATH").get());
    }

    @Test
    @DisplayName("Test for find document by wrong path")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDocumentId_whenFindDocumentByWrongPath_thenDocumentObject(){
        assertTrue(documentRepository.findDocumentByPath("123456").isEmpty());
    }
}
