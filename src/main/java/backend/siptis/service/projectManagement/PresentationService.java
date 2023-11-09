package backend.siptis.service.projectManagement;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationService {

    ServiceAnswer createPresentation(Long idProyecto, PhaseName fase);

    ServiceAnswer gradePresentation(Long idPresentacion);

    ServiceAnswer attachFile(Long idPresentacion, MultipartFile file, String context);

    ServiceAnswer removeFile(Long idPresentacion, String context);

    ServiceAnswer delete(Long idPresentacion);

    ServiceAnswer getLastReviewsFromAPresentation(Long idProject);
}
