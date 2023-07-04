package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.service.projectManagement.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    public ProjectService projectService;

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable ("id") Long idProyecto){
        ServiceAnswer serviceAnswer = projectService.getPresentations(idProyecto);
        return createResponseEntity(serviceAnswer);
    }
    @GetMapping("/")
    public ResponseEntity<?> getProyectos(){
        ServiceAnswer serviceAnswer = projectService.getProjects();
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPaginatedProjects(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjects(pageNumber, pageSize);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsByName(pageNumber, pageSize, name);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsByModality(pageNumber, pageSize, modality);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsByArea(pageNumber, pageSize, area);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsBySubArea(pageNumber, pageSize, subarea);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsByFilters(pageNumber, pageSize, name, modality, area, subarea);
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

    @GetMapping("/getProjectInfoToReview/{projectId}/{reviewerId}")
    public ResponseEntity<?> getProjectInfoToReview(@PathVariable("projectId") Long projectId, @PathVariable("reviewerId") Long reviewerId){
        ServiceAnswer serviceAnswer = projectService.getProjectInfoToReview(projectId, reviewerId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK && serviceAnswer.getServiceMessage() != ServiceMessage.THERE_IS_NO_PRESENTATION_YET){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/allInfo/{projectId}")
    public ResponseEntity<?> getAllInfo(@PathVariable("projectId") Long projectId){
        ServiceAnswer serviceAnswer = projectService.getAllProjectInfo(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/getInfoToAssignTribunals/{projectId}")
    public ResponseEntity<?> getInfoToAssignTribunals(@PathVariable("projectId") Long projectId){
        ServiceAnswer serviceAnswer = projectService.getProjectInfoToAssignTribunals(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/assignTribunals")
    public ResponseEntity<?> assignTribunal(@RequestBody AssignTribunalsDTO assignTribunalsDTO){
        ServiceAnswer serviceAnswer = projectService.assignTribunals(assignTribunalsDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/schedulesToAssignDefense/{projectId}")
    public ResponseEntity<?> getSchedulesToAssignDefense(@PathVariable("projectId") Long projectId){
        ServiceAnswer serviceAnswer = projectService.getSchedulesInfoToAssignADefense(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/createDefense")
    public ResponseEntity<?> createDefense(@RequestBody DefenseDTO defenseDTO){
        ServiceAnswer serviceAnswer = projectService.addDefense(defenseDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/defenses/{tribunalID}")
    public ResponseEntity<?> getProjectsToDefenseOrDefended(@PathVariable("tribunalID") Long tribunalID){
        ServiceAnswer serviceAnswer = projectService.getProjectsToDefenseOrDefended(tribunalID);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/withAndWithoutTribunals")
    public ResponseEntity<?> getProjectsWithAndWithoutTribunals(){
        ServiceAnswer serviceAnswer = projectService.getProjectsWithoutAndWithTribunals();
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/withAndWithoutDefensePlace")
    public ResponseEntity<?> getProjectsWithAndWithoutDefensePlace(){
        ServiceAnswer serviceAnswer = projectService.getProjectsWithoutAndWithDefensePlace();
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
