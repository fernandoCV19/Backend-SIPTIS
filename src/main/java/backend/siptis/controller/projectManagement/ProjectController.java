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
    public ProjectService proyectoGradoService;

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable ("id") Long idProyecto){
        ServiceAnswer serviceAnswer = proyectoGradoService.getPresentations(idProyecto);
        return crearResponseEntity(serviceAnswer);
    }
    @GetMapping("/")
    public ResponseEntity<?> getProyectos(){
        ServiceAnswer serviceAnswer = proyectoGradoService.getProjects();
        return crearResponseEntity(serviceAnswer);
    }
    private ResponseEntity<?> crearResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
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

    @GetMapping("/getInfoToAssignTribunals/{idProject}")
    public ResponseEntity<?> getInfoToAssignTribunals(@PathVariable("idProject") Long idProject){
        ServiceAnswer serviceAnswer = proyectoGradoService.getProjectInfoToAssignTribunals(idProject);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/assignTribunals")
    public ResponseEntity<?> assignTribunal(@RequestBody AssignTribunalsDTO assignTribunalsDTO){
        ServiceAnswer serviceAnswer = proyectoGradoService.assignTribunals(assignTribunalsDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/schedulesToAssignDefense/{idProject}")
    public ResponseEntity<?> getSchedulesToAssignDefense(@PathVariable("idProject") Long idProject){
        ServiceAnswer serviceAnswer = proyectoGradoService.getSchedulesInfoToAssignADefense(idProject);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/createDefense")
    public ResponseEntity<?> createDefense(@RequestBody DefenseDTO defenseDTO){
        ServiceAnswer serviceAnswer = proyectoGradoService.addDefense(defenseDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/defenses/{tribunalID}")
    public ResponseEntity<?> getProjectsToDefenseOrDefended(@PathVariable("tribunalID") Long tribunalID){
        ServiceAnswer serviceAnswer = proyectoGradoService.getProjectsToDefenseOrDefended(tribunalID);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
