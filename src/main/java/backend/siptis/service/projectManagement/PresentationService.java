package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationService {

    ServiceAnswer createPresentation (Long idProyecto, String fase);

    ServiceAnswer gradePresentation (Long idPresentacion);

    ServiceAnswer attachFile (Long idPresentacion, MultipartFile file, String context);

    ServiceAnswer removeFile (Long idPresentacion, String context);

    ServiceAnswer delete (Long idPresentacion);

    ServiceAnswer downloadFileFromCloud(String key);
}
