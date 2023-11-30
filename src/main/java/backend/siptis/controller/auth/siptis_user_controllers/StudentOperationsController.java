package backend.siptis.controller.auth.siptis_user_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceStudentOperations;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(name = ControllerConstants.SiptisUser.TAG_NAME, description = ControllerConstants.SiptisUser.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class StudentOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceStudentOperations siptisUserServiceStudentOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Register user with STUDENT role")
    @PostMapping("/register/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ControllerAnswer> registerStudent(@Valid @RequestBody RegisterStudentDTO dto) {
        ServiceAnswer student = siptisUserServiceStudentOperations.registerStudent(dto);
        return createResponseEntity(student);
    }

    @Operation(summary = "get number of students in career")
    @GetMapping("/studentsInCareer/{careerName}")
    public ResponseEntity<ControllerAnswer> getStudentsInCareer(@PathVariable String careerName) {
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getNumberStudentsCareer(careerName);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "get available students for register a new project")
    @GetMapping("/list/availableStudents")
    public ResponseEntity<ControllerAnswer> getAvailableStudents(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getAvailableStudents(search, pageable);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "get own career")
    @GetMapping("/userCareer")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<ControllerAnswer> getCareer(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "get career from other user")
    @GetMapping("/userCareer/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getCareer(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "get number of students by year and career")
    @GetMapping("/studentsByYear/{careerName}")
    public ResponseEntity<ControllerAnswer> getNumberOfStudentsByYearAndCareer(@PathVariable String careerName) {
        ServiceAnswer answerService =
                siptisUserServiceStudentOperations.getNumberOfStudentsByYearAndCareer(careerName);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
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
