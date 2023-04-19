package backend.siptis.service.projectManagement;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CloudManagementService {
    String putObject(MultipartFile multipartFile, String carpeta);

    ByteArrayOutputStream getObject(String key) ;

    void deleteObject(String key);

    String getObjectURL(String key);

    List<String> getObjectslistFromFolder(String folderKey) ;
}
