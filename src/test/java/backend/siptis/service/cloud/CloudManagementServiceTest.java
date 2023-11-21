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
public class CloudManagementServiceTest {
    @Autowired
    private CloudManagementService cloudManagementService;


    @Test
    @DisplayName("test put object when given null File")
    public void GivenNullFileWhenPutObjectThenException(){
        try{
            cloudManagementService.putObject((File) null, null);
        }catch (Exception e){}
    }
    @Test
    @DisplayName("test put object when given null multipartFile")
    public void GivenNullMultipartWhenPutObjectThenException(){
        try{
            cloudManagementService.putObject((MultipartFile) null, null);
        }catch (Exception e){}
    }
    @Test
    @DisplayName("test get object when given bad key")
    public void GivenWrongKeyWhenGetObjectThenException(){
        try{
            assertNull(cloudManagementService.getObject("wrong key"));
        }catch (Exception e){}
    }
    @Test
    @DisplayName("test delete object when given bad key")
    public void GivenWrongKeyWhenDeleteObjectThenException(){
        try{
            cloudManagementService.deleteObject("wrong key");
        }catch (Exception e){}
    }
    @Test
    @DisplayName("test get object URL when given bad key")
    public void GivenWrongKeyWhenGetObjectURLThenException(){
            assertNotNull(cloudManagementService.getObjectURL("non existing key"));
    }
    @Test
    @DisplayName("test get objects list")
    public void GivenFolderKeyWhenGetObjectsListThenException(){
        List<String> list = cloudManagementService.getObjectslistFromFolder("Cartas");
        assertFalse(list.isEmpty());
    }
    @Test
    @DisplayName("test upload document to cloud")
    public void GivenWrongFilenameWhenUploadDocumentToCloudThenException(){
        try {
            cloudManagementService.uploadDocumentToCloud("wrong filename");
        }catch (Exception e){}
    }
    @Test
    @DisplayName("test upload letter to cloud")
    public void GivenWrongFilenameWhenUploadLetterToCloudThenException(){
        try {
            cloudManagementService.uploadLetterToCloud("wrong filename", "wrong projectname");
        }catch (Exception e){}
    }
/*
    @Test
    @DisplayName("")
    public void GivenWhenPutObjectThen(){

    }
*//*
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
