package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;
import backend.siptis.service.editorsAndReviewers.ProjectTribunalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tribunal")
@RequiredArgsConstructor
@CrossOrigin
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

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getProjectsReadyToDefense(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/defendedProjects/{id}")
    public ResponseEntity<?> getdefendedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTribunalService.getAllProjectsDefendedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    public ResponseEntity<?> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer){
        ServiceAnswer serviceAnswer = projectTribunalService.acceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)){
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/removeAccepted/{idProject}/{idReviewer}")
    public ResponseEntity<?> removeAcceptedFromAProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer){
        ServiceAnswer serviceAnswer = projectTribunalService.removeAcceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)){
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/reviewDefense")
    public ResponseEntity<?> reviewDefense(@RequestBody ReviewADefenseDTO reviewADefenseDTO){
        ServiceAnswer serviceAnswer = projectTribunalService.reviewADefense(reviewADefenseDTO);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)){
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @DeleteMapping("/removeTribunals/{id}")
    public ResponseEntity<?> removeTribunalsFromAProject(@PathVariable("id") Long projectId){
        ServiceAnswer serviceAnswer = projectTribunalService.removeTribunals(projectId);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)){
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
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
