package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.projectManagement.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    public ProjectService proyectoGradoService;

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable ("id") Long idProyecto){
        ServiceAnswer serviceAnswer = proyectoGradoService.obtenerPresentaciones(idProyecto);
        return crearResponseEntity(serviceAnswer);
    }
    @GetMapping("/")
    public ResponseEntity<?> getProyectos(){
        ServiceAnswer serviceAnswer = proyectoGradoService.obtenerProyectos();
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
}
