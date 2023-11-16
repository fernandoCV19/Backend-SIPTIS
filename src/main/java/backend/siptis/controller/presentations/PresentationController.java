package backend.siptis.controller.presentations;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.presentations.PresentationService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.Presentation.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class PresentationController {

    private final PresentationService presentationService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestHeader(name = "Authorization") String token,
                             @RequestPart("bluebook") @Nullable MultipartFile bluebook,
                             @RequestPart("project") @Nullable MultipartFile project) {
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        ServiceAnswer serviceAnswer = presentationService.createPresentation((long) projectId, bluebook, project);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviews/{presentationId}")
    ResponseEntity<?> getReviewsFromPresentation(@PathVariable("presentationId") Long presentationId) {
        ServiceAnswer serviceAnswer = presentationService.getReviewsFromAPresentation(presentationId);
        return createResponseEntity(serviceAnswer);
    }

    @DeleteMapping("/{presentationId}")
    ResponseEntity<?> deletePresentation(@PathVariable("presentationId") Long presentationId) {
        ServiceAnswer serviceAnswer = presentationService.deletePresentation(presentationId);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/getLastReviews/{id}")
    ResponseEntity<?> getLastReviews(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = presentationService.getReviewsFromLastPresentation(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;


        if (serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }


}
