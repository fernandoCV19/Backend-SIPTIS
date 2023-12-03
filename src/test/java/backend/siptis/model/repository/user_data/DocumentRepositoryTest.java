package backend.siptis.model.repository.user_data;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.user_data.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;
    private Document document;

    @BeforeEach
    void createPlaceToDefense() {
        document = new Document();
        document.setPath("path");
        document.setSiptisUser(new SiptisUser());
        document.setDescription("document for test");
    }

    @Test
    @DisplayName("Test for find document by path")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenDocumentPath_whenFindDocumentByPath_thenDocumentObject() {
        assertNotNull(documentRepository.findDocumentByPath("PATH").get());
    }

    @Test
    @DisplayName("Test for find document by wrong path")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenDocumentId_whenFindDocumentByWrongPath_thenDocumentObject() {
        assertTrue(documentRepository.findDocumentByPath("123456").isEmpty());
    }
}
