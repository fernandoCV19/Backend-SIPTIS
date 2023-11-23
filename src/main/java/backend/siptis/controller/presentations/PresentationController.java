package backend.siptis.controller.presentations;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.presentations.PresentationService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.util.List;

@Tag(name = ControllerConstants.Presentation.TAG_NAME, description = ControllerConstants.Presentation.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Presentation.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class PresentationController {

    private final PresentationService presentationService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Create new presentation")
    @PostMapping("/create")
    ResponseEntity<ControllerAnswer> create(@RequestHeader(name = "Authorization") String token,
                             @RequestPart("bluebook") @Nullable MultipartFile bluebook,
                             @RequestPart("project") @Nullable MultipartFile project) {
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        ServiceAnswer serviceAnswer = presentationService.createPresentation((long) projectId, bluebook, project);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get reviews from a presentation")
    @GetMapping("/reviews/{presentationId}")
    ResponseEntity<ControllerAnswer> getReviewsFromPresentation(@PathVariable("presentationId") Long presentationId) {
        ServiceAnswer serviceAnswer = presentationService.getReviewsFromAPresentation(presentationId);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Delete a presentation")
    @DeleteMapping("/{presentationId}")
    ResponseEntity<ControllerAnswer> deletePresentation(@PathVariable("presentationId") Long presentationId) {
        ServiceAnswer serviceAnswer = presentationService.deletePresentation(presentationId);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get last reviews from a presentation")
    @GetMapping("/getLastReviews/{id}")
    ResponseEntity<ControllerAnswer> getLastReviews(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = presentationService.getReviewsFromLastPresentation(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;


        if (serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }


}
