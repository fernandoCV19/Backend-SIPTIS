package backend.siptis.controller.semester;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import backend.siptis.service.semester.SemesterInformationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semester")
@AllArgsConstructor
@CrossOrigin
public class SemesterInformationController {

    private final SemesterInformationRepository repository;
    private final SemesterInformationService semesterService;

    @GetMapping("/checkActiveSemester")
    public ResponseEntity<?> checkActiveSemester(){
        ServiceAnswer answerService = semesterService.existActiveSemester();
        return createResponse(answerService);
    }

    @GetMapping("/getActiveSemesterInformation")
    public ResponseEntity<?> getActiveSemester(){
        ServiceAnswer answerService = semesterService.getCurrentSemester();
        return createResponse(answerService);
    }

    @PostMapping("/startSemester")
    public ResponseEntity<?> createSemester(
            @RequestBody SemesterInformationDTO dto){
        ServiceAnswer answerService = semesterService.startSemester(dto);
        return createResponse(answerService);
    }

    @PutMapping("/closeSemester/{id}")
    public ResponseEntity<?> closeSemester(@PathVariable int id){
        Long semesterId = Long.valueOf(id);
        ServiceAnswer answerService = semesterService.closeSemester(semesterId);
        return createResponse(answerService);
    }


    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }
        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
