package backend.siptis.service.gestionProyecto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CloudManagementService {
    String putObject(MultipartFile multipartFile, String carpeta);

    String getObject(String key) ;

    void deleteObject(String key);

    String getObjectURL(String key);

    List<String> getObjectslistFromFolder(String folderKey) ;
}
