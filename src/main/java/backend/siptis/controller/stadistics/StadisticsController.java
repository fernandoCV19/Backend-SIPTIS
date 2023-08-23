package backend.siptis.controller.stadistics;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.Stadistics.StadisticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadistics")
@AllArgsConstructor
@CrossOrigin
public class StadisticsController {

    @Autowired
    private final StadisticsService stadisticsService;

    @GetMapping("/studentsByCareer")
    public ResponseEntity<?> getStudentsByCareer(){

        ServiceAnswer answerService = stadisticsService.StudentsByCareerStadistics();

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/userByArea")
    public ResponseEntity<?> getUsersByArea(){

        ServiceAnswer answerService = stadisticsService.UserByAreaStadistics();

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/projectsByArea/{careerId}")
    public ResponseEntity<?> getProjectsByAreaAndCareer(@PathVariable int careerId){

        ServiceAnswer answerService = stadisticsService.ProyectsByAreaAndCareer(careerId);

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/studentsByYear")
    public ResponseEntity<?> getStudentsByYear(){

        ServiceAnswer answerService = stadisticsService.getStudentsByYear();

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/studentsByYearAndCareer/{career}")
    public ResponseEntity<?> getStudentsByYearAndCareer(@PathVariable String career){

        ServiceAnswer answerService =
                stadisticsService.getStudentsByYearAndCareer(career);

        return crearResponseEntityRegistrar(answerService);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }


        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
