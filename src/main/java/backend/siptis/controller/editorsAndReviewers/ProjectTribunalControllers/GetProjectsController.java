package backend.siptis.controller.editorsAndReviewers.ProjectTribunalControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editorsAndReviewers.projectTribunalServices.ProjectTribunalServiceGetProjects;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.ProjecTribunal.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class GetProjectsController {

    private final ProjectTribunalServiceGetProjects projectTribunalServiceGetProjects;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedReviewedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getProjectsReadyToDefense(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/defendedProjects/{id}")
    public ResponseEntity<?> getdefendedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsDefendedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
