package backend.siptis.service.cloud;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CloudManagementService {
    String putObject(MultipartFile multipartFile, String carpeta);

    String putObject(File file, String carpeta);

    ByteArrayOutputStream getObject(String key) ;

    void deleteObject(String key);

    String getObjectURL(String key);

    List<String> getObjectslistFromFolder(String folderKey) ;

     String uploadDocumentToCloud(String filename);

     String uploadLetterToCloud(String filename);
}
