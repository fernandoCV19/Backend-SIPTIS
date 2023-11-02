package backend.siptis.controller.semester;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.semester.EditSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import backend.siptis.service.semester.SemesterInformationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/semester")
@AllArgsConstructor
@CrossOrigin
public class SemesterInformationController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SEMESTER_STARTED, ServiceMessage.SEMESTER_INFORMATION,
                    ServiceMessage.SEMESTER_ENDED, ServiceMessage.SEMESTER_DATE_EDITED));
    private final Set<ServiceMessage> badRequestResponse = new HashSet<>(
            List.of(ServiceMessage.NO_CURRENT_SEMESTER, ServiceMessage.ERROR,
                    ServiceMessage.SEMESTER_ALREADY_EXIST));

    private final SemesterInformationService semesterService;

    @GetMapping("/checkActiveSemester")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> checkActiveSemester(){
        ServiceAnswer answerService = semesterService.existActiveSemester();
        return createResponse(answerService);
    }

    @GetMapping("/getActiveSemesterInformation")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getActiveSemester(){
        ServiceAnswer answerService = semesterService.getCurrentSemester();
        return createResponse(answerService);
    }

    @GetMapping("/getcurrentSemesterPeriod")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getcurrentSemesterPeriod(){
        ServiceAnswer answerService = semesterService.getCurrentPeriod();
        return createResponse(answerService);
    }

    @PostMapping("/startSemester")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createSemester(
            @RequestBody SemesterInformationDTO dto){
        ServiceAnswer answerService = semesterService.startSemester(dto);
        return createResponse(answerService);
    }

    @PutMapping("/editSemester")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editSemester(
            @Valid @RequestBody EditSemesterInfoDTO dto){
        // Long semesterId = Long.valueOf(id);
        ServiceAnswer answerService = semesterService.editSemester(dto);
        return createResponse(answerService);
    }

    @PutMapping("/closeSemester/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> closeSemester(@PathVariable int id){
        Long semesterId = Long.valueOf(id);
        ServiceAnswer answerService = semesterService.closeSemester(semesterId);
        return createResponse(answerService);
    }


    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        if(okResponse.contains(messageService)){
            httpStatus = HttpStatus.OK;
        } else if (badRequestResponse.contains(messageService)){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
