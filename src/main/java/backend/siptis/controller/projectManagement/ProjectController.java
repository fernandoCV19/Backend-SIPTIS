package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import backend.siptis.service.projectManagement.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    public ProjectService projectService;


    @PostMapping("/newProject")
    public ResponseEntity<?> createProject(@Valid @RequestBody NewProjectDTO dto) {
        ServiceAnswer serviceAnswer = projectService.createProject(dto);

        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.BAD_REQUEST;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/projectList")
    public ResponseEntity<?> searchProjects(String search, Pageable pageable) {
        ServiceAnswer serviceAnswer = projectService.getProjectsList(search, pageable);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<?> getProjectInformation(@PathVariable("id") Long idProject) {
        ServiceAnswer answer = projectService.getProjectInfo(idProject);
        return createResponseEntity(answer);
    }

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable("id") Long idProyecto) {
        ServiceAnswer serviceAnswer = projectService.getPresentations(idProyecto);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/")
    public ResponseEntity<?> getProyectos() {
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsByArea(pageNumber, pageSize, area);
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
        ServiceAnswer serviceAnswer = projectService.getPaginatedCompletedProjectsBySubArea(pageNumber, pageSize, subarea);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
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

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/getProjectInfoToReview/{projectId}/{reviewerId}")
    public ResponseEntity<?> getProjectInfoToReview(@PathVariable("projectId") Long projectId, @PathVariable("reviewerId") Long reviewerId) {
        ServiceAnswer serviceAnswer = projectService.getProjectInfoToReview(projectId, reviewerId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK && serviceAnswer.getServiceMessage() != ServiceMessage.THERE_IS_NO_PRESENTATION_YET) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/allInfo/{projectId}")
    public ResponseEntity<?> getAllInfo(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectService.getAllProjectInfo(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/getInfoToAssignTribunals/{projectId}")
    public ResponseEntity<?> getInfoToAssignTribunals(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectService.getProjectInfoToAssignTribunals(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/getInvolvedPeople/{projectId}")
    public ResponseEntity<?> getInvolvedPeople(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectService.getInvolvedPeople(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/schedulesToAssignDefense/{projectId}")
    public ResponseEntity<?> getSchedulesToAssignDefense(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectService.getSchedulesInfoToAssignADefense(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/defenses/{tribunalID}")
    public ResponseEntity<?> getProjectsToDefenseOrDefended(@PathVariable("tribunalID") Long tribunalID) {
        ServiceAnswer serviceAnswer = projectService.getProjectsToDefenseOrDefended(tribunalID);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/withAndWithoutTribunals")
    public ResponseEntity<?> getProjectsWithAndWithoutTribunals() {
        ServiceAnswer serviceAnswer = projectService.getProjectsWithoutAndWithTribunals();
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/withAndWithoutDefensePlace")
    public ResponseEntity<?> getProjectsWithAndWithoutDefensePlace() {
        ServiceAnswer serviceAnswer = projectService.getProjectsWithoutAndWithDefensePlace();
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/modalityAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProyectsByModalityAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectService.getNumberOfProjectsByModalityAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/areaAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProyectsByAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectService.getNumberOfProjectsByAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/subareaAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProyectsBySubAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectService.getNumberOfProjectsBySubAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/projectsByCareer/{careerId}")
    public ResponseEntity<?> getNumberProjectsByCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectService.getNumberProjectsByCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/periodAndCareer/{careerId}")
    public ResponseEntity<?> getNumberProjectsByPeriodAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectService.getNumberProjectsByPeriodAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
