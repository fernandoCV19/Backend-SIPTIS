package backend.siptis.service.projectManagement;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationService {

    ServiceAnswer createPresentation(Long idProject, PhaseName phase);

    ServiceAnswer gradePresentation(Long idPresentation);

    ServiceAnswer getReviewsFromAPresentation(Long idPresentation);

    ServiceAnswer attachFile(Long idPresentation, MultipartFile file, String context);

    ServiceAnswer removeFile(Long idPresentation, String context);

    ServiceAnswer delete(Long idPresentation);

    ServiceAnswer getLastReviewsFromAPresentation(Long idProject);
}
