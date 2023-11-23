package backend.siptis.service.presentations;

import backend.siptis.commons.ServiceAnswer;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewService {

    ServiceAnswer addReview(Long projectId, Long userId, MultipartFile multipartFile, String commentary);
}
