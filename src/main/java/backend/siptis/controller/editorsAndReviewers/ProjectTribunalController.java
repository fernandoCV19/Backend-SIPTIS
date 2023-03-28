package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.editorsAndReviewers.ProjectTribunalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tribunal")
@RequiredArgsConstructor
public class ProjectTribunalController {

    private final ProjectTribunalService projectTribunalService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectsWithoutReview(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsNotReviewedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/projectsReadyToDefense/{id}")
    public ResponseEntity<?> getProjectsReadyToDefense(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/defendedProjects/{id}")
    public ResponseEntity<?> getdefendedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsDefendedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}
