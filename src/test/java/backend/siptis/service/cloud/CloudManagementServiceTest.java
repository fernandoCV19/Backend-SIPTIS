package backend.siptis.service.cloud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CloudManagementServiceTest {

    private CloudManagementService cloudManagementService;

    @Autowired
    CloudManagementServiceTest(CloudManagementService cloudManagementService){
        this.cloudManagementService = cloudManagementService;
    }
/*
    @Test
    @DisplayName("")
    public void GivenWhenPutObjectThen(){

    }
*/

    /*
    @Test
    @DisplayName("Test for find by id")
    public void

    String putObject(MultipartFile multipartFile, String carpeta);
    String putObject(File file, String carpeta);
    ByteArrayOutputStream getObject(String key);
    void deleteObject(String key);
    String getObjectURL(String key);
    List<String> getObjectslistFromFolder(String folderKey);
    String uploadDocumentToCloud(String filename);
    String uploadLetterToCloud(String filename, String projectName);

     */
}
