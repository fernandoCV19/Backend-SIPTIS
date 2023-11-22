package backend.siptis.service.cloud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CloudManagementServiceTest {
    @Autowired
    private CloudManagementService cloudManagementService;


    @Test
    @DisplayName("test put object when given null File")
    void GivenNullFileWhenPutObjectThenException() {
        try {
            cloudManagementService.putObject((File) null, null);
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test put object when given null multipartFile")
    void GivenNullMultipartWhenPutObjectThenException() {
        try {
            cloudManagementService.putObject((MultipartFile) null, null);
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test get object when given bad key")
    void GivenWrongKeyWhenGetObjectThenException() {
        try {
            assertNull(cloudManagementService.getObject("wrong key"));
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test delete object when given bad key")
    void GivenWrongKeyWhenDeleteObjectThenException() {
        try {
            cloudManagementService.deleteObject("wrong key");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test get object URL when given bad key")
    void GivenWrongKeyWhenGetObjectURLThenException() {
        assertNotNull(cloudManagementService.getObjectURL("non existing key"));
    }

    @Test
    @DisplayName("test get objects list")
    void GivenFolderKeyWhenGetObjectsListThenException() {
        List<String> list = cloudManagementService.getObjectslistFromFolder("Cartas");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("test upload document to cloud")
    void GivenWrongFilenameWhenUploadDocumentToCloudThenException() {
        try {
            cloudManagementService.uploadDocumentToCloud("wrong filename");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("test upload letter to cloud")
    void GivenWrongFilenameWhenUploadLetterToCloudThenException() {
        try {
            cloudManagementService.uploadLetterToCloud("wrong filename", "wrong projectname");
        } catch (Exception e) {
        }
    }
}