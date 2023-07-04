package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.service.projectManagement.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    public ProjectService proyectoGradoService;

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable ("id") Long idProyecto){
        ServiceAnswer serviceAnswer = proyectoGradoService.getPresentations(idProyecto);
        return createResponseEntity(serviceAnswer);
    }
    @GetMapping("/")
    public ResponseEntity<?> getProyectos(){
        ServiceAnswer serviceAnswer = proyectoGradoService.getProjects();
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPaginatedProjects(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjects(pageNumber, pageSize);
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
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjectsByName(pageNumber, pageSize, name);
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
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjectsByModality(pageNumber, pageSize, modality);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK)
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
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjectsByArea(pageNumber, pageSize, area);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK)
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
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjectsBySubArea(pageNumber, pageSize, subarea);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.NOT_FOUND;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/page/filter")
    public ResponseEntity<?> getPaginatedProjectsByFilter(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String modality,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String subarea
    ) {
        ServiceAnswer serviceAnswer = proyectoGradoService.getPaginatedCompletedProjectsByFilters(pageNumber, pageSize, name, modality, area, subarea);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/getProjectInfoToReview/{idProject}/{idReviewer}")
    public ResponseEntity<?> getProjectInfoToReview(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer){
        ServiceAnswer serviceAnswer = proyectoGradoService.getProjectInfoToReview(idProject, idReviewer);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK && serviceAnswer.getServiceMessage() != ServiceMessage.THERE_IS_NO_PRESENTATION_YET){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/allInfo/{idProject}")
    public ResponseEntity<?> getAllInfo(@PathVariable("idProject") Long idProject){
        ServiceAnswer serviceAnswer = proyectoGradoService.getAllProjectInfo(idProject);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
