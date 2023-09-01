package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewService {

    public ServiceAnswer addReview(Long projectId, Long userId, MultipartFile multipartFile, String commentary);
}