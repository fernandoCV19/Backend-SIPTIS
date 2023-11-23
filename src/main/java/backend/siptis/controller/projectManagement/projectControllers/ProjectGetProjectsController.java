package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.project.ProjectServiceGetProjects;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Project.TAG_NAME, description = ControllerConstants.Project.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectGetProjectsController {

    private final ProjectServiceGetProjects projectServiceGetProjects;

    @Operation(summary = "Search paginated projets by name")
    @GetMapping("/projectList")
    public ResponseEntity<?> searchProjects(String search, Pageable pageable) {
        ServiceAnswer serviceAnswer = projectServiceGetProjects.getProjectsList(search, pageable);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get all proyects")
    @GetMapping("/")
    public ResponseEntity<?> getProyectos() {
        ServiceAnswer serviceAnswer = projectServiceGetProjects.getProjects();
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get projects to defense or defeneded by tribunal id")
    @GetMapping("/defenses/{tribunalID}")
    public ResponseEntity<?> getProjectsToDefenseOrDefended(@PathVariable("tribunalID") Long tribunalID) {
        ServiceAnswer serviceAnswer = projectServiceGetProjects.getProjectsToDefenseOrDefended(tribunalID);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get projets all project on tribunal phase")
    @GetMapping("/withAndWithoutTribunals")
    public ResponseEntity<?> getProjectsWithAndWithoutTribunals() {
        ServiceAnswer serviceAnswer = projectServiceGetProjects.getProjectsWithoutAndWithTribunals();
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get projects with and without defense place")
    @GetMapping("/withAndWithoutDefensePlace")
    public ResponseEntity<?> getProjectsWithAndWithoutDefensePlace() {
        ServiceAnswer serviceAnswer = projectServiceGetProjects.getProjectsWithoutAndWithDefensePlace();
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
