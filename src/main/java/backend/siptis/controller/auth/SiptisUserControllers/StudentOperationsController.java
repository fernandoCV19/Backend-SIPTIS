package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceStudentOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class StudentOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceStudentOperations siptisUserServiceStudentOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @PostMapping("/register/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody RegisterStudentDTO dto) {
        ServiceAnswer student = siptisUserServiceStudentOperations.registerStudent(dto);
        return createResponseEntity(student);
    }


    @GetMapping("/studentsInCareer/{careerId}")
    public ResponseEntity<?> getStudentsInCareer(@PathVariable Long careerId) {
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getNumberStudentsCareer(careerId);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userCareer")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> getCareer(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userCareer/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getCareer(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/studentsByYear/{careerId}")
    public ResponseEntity<?> getNumberOfStudentsByYearAndCareer(@PathVariable Long careerId) {
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getNumberOfStudentsByYearAndCareer(careerId);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (okResponse.contains(messageService))
            httpStatus = HttpStatus.OK;

        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
