package backend.siptis.controller.editoresYRevisores;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.editoresYRevisores.ProjectSupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supervisor")
@RequiredArgsConstructor
public class ProjectSupervisorController {

    private final ProjectSupervisorService projectSupervisorService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectWithoutReview(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedByIdSupervisor(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedReviewedByIdSupervisor(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getAcceptedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsAcceptedByIsSupervisor(id);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }
}
