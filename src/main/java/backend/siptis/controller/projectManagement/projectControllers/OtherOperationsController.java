package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.projectManagement.project.ProjectServiceOtherOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = ControllerConstants.Project.TAG_NAME, description = ControllerConstants.Project.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class OtherOperationsController {

    private final ProjectServiceOtherOperations projectServiceOtherOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Get project presentations by id")
    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentationsById(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = projectServiceOtherOperations.getPresentations(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get project presentations")
    @GetMapping("/presentations")
    public ResponseEntity<?> getPresentations(@RequestHeader(name = "Authorization") String token) {
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        ServiceAnswer serviceAnswer = projectServiceOtherOperations.getPresentations((long) projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get involved people by project id")
    @GetMapping("/getInvolvedPeople/{projectId}")
    public ResponseEntity<?> getInvolvedPeople(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectServiceOtherOperations.getInvolvedPeople(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get schedules to assign defense by project id")
    @GetMapping("/schedulesToAssignDefense/{projectId}")
    public ResponseEntity<?> getSchedulesToAssignDefense(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectServiceOtherOperations.getSchedulesInfoToAssignADefense(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
