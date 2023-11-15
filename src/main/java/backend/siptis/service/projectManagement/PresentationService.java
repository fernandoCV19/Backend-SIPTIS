package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface PresentationService {

    ServiceAnswer createPresentation(Long idProject, MultipartFile bluebookFile, MultipartFile projectFile);

    ServiceAnswer getReviewsFromAPresentation(Long idPresentation);

    ServiceAnswer deletePresentation(Long idPresentation);

    ServiceAnswer getReviewsFromLastPresentation(Long idProject);
}
