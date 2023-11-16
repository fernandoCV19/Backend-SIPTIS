package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
public class GeneralUserOperationsController {

    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));

    @GetMapping("/personalInformation/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getInfoById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/personalInformation")
    public ResponseEntity<?> getPersonalInfo(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas")
    @PreAuthorize("hasAuthority('TRIBUNAL')")
    public ResponseEntity<?> getAreas(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserAreasById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAreasById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserAreasById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getStudentList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "STUDENT", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/teachers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getTeacherList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TEACHER", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/tribunals")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getTribunalsList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TRIBUNAL", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/generalUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getNormalUserList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getNormalUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/potentialTutors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getPotentialTutors(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TUTOR", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/potentialSupervisors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getPotentialSupervisors(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "SUPERVISOR", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/personal-activities")
    public ResponseEntity<?> getPersonalProjectActivities(@RequestHeader(name = "Authorization") String token, Pageable pageable) {
        Long idL = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceGeneralUserOperations.getPersonalActivities(idL, pageable);
        return createResponseEntity(answer);
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
