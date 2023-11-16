package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.project.ProjectServiceGetPaginatedProjects;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class GetPaginatedProjectsController {

    private final ProjectServiceGetPaginatedProjects projectServiceGetPaginatedProjects;

    @GetMapping("/page")
    public ResponseEntity<?> getPaginatedProjects(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        ServiceAnswer serviceAnswer = projectServiceGetPaginatedProjects.getPaginatedCompletedProjects(pageNumber, pageSize);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/page/name")
    public ResponseEntity<?> getPaginatedProjectsByName(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = true) String name
    ) {
        ServiceAnswer serviceAnswer = projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByName(pageNumber, pageSize, name);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/page/modality")
    public ResponseEntity<?> getPaginatedProjectsByModality(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = true) String modality
    ) {
        ServiceAnswer serviceAnswer = projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByModality(pageNumber, pageSize, modality);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.NOT_FOUND;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/page/area")
    public ResponseEntity<?> getPaginatedProjectsByArea(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = true) String area
    ) {
        ServiceAnswer serviceAnswer = projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByArea(pageNumber, pageSize, area);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.NOT_FOUND;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/page/subarea")
    public ResponseEntity<?> getPaginatedProjectsBySubArea(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = true) String subarea
    ) {
        ServiceAnswer serviceAnswer = projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsBySubArea(pageNumber, pageSize, subarea);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.NOT_FOUND;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
