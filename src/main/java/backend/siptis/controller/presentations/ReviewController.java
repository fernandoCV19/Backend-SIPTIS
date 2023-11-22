package backend.siptis.controller.presentations;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.presentations.ReviewService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = ControllerConstants.Review.TAG_NAME, description = ControllerConstants.Review.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Review.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Create a review")
    @PostMapping(value = "/createReview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ControllerAnswer> attachFile(@RequestParam Long projectId, @RequestParam Long userId, @RequestParam MultipartFile file, @RequestParam String commentary) {
        ServiceAnswer serviceAnswer = reviewService.addReview(projectId, userId, file, commentary);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
