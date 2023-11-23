package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class GeneralUserOperationsController {

    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    @Operation(summary = "Get personal information from other user")
    @GetMapping("/personalInformation/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getInfoById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get own personal information")
    @GetMapping("/personalInformation")
    public ResponseEntity<ControllerAnswer> getPersonalInfo(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get own user areas")
    @GetMapping("/userAreas")
    @PreAuthorize("hasAuthority('TRIBUNAL')")
    public ResponseEntity<ControllerAnswer> getAreas(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserAreasById(id);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get user areas from other user")
    @GetMapping("/userAreas/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getAreasById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserAreasById(id);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of users with STUDENT role")
    @GetMapping("/list/students")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getStudentList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "STUDENT", pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of users with TEACHER role")
    @GetMapping("/list/teachers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getTeacherList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TEACHER", pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of users with TRIBUNAL role")
    @GetMapping("/list/tribunals")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getTribunalsList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TRIBUNAL", pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of general users")
    @GetMapping("/list/generalUsers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getNormalUserList(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getNormalUserList(search, pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of users with TUTOR role")
    @GetMapping("/list/potentialTutors")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getPotentialTutors(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "TUTOR", pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get list of users with SUPERVISOR role")
    @GetMapping("/list/potentialSupervisors")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getPotentialSupervisors(String search, Pageable pageable) {
        ServiceAnswer answerService = siptisUserServiceGeneralUserOperations.getUserList(search, "SUPERVISOR", pageable);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "Get own personal project activities")
    @GetMapping("/personal-activities")
    public ResponseEntity<ControllerAnswer> getPersonalProjectActivities(@RequestHeader(name = "Authorization") String token, Pageable pageable) {
        Long idL = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceGeneralUserOperations.getPersonalActivities(idL, pageable);
        return createResponseEntity(answer);
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
