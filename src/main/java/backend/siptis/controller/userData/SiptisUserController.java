package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.authentication.RefreshTokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.service.userData.SiptisUserService;
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

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class SiptisUserController {

    private final SiptisUserService siptisUserService;

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final Set<ServiceMessage> badRequestResponse = new HashSet<>(
            List.of(ServiceMessage.ERROR_REGISTER_ACCOUNT, ServiceMessage.EMAIL_ALREADY_EXIST,
                    ServiceMessage.CI_ALREADY_EXIST, ServiceMessage.COD_SIS_ALREADY_EXIST,
                    ServiceMessage.ERROR_VALIDATION, ServiceMessage.EMAIL_ALREADY_EXIST,
                    ServiceMessage.COD_SIS_ALREADY_EXIST, ServiceMessage.CI_ALREADY_EXIST,
                    ServiceMessage.ERROR_LOG_IN, ServiceMessage.ERROR_BAD_CREDENTIALS, ServiceMessage.EXPIRED_REFRESH_TOKEN));


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LogInDTO logInDTO) {
        ServiceAnswer answerService = siptisUserService.logIn(logInDTO);
        return createResponseEntity(answerService);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterAdminDTO dto) {
        ServiceAnswer admin = siptisUserService.registerAdmin(dto);
        return createResponseEntity(admin);
    }

    @PostMapping("/register/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody RegisterStudentDTO dto) {
        ServiceAnswer student = siptisUserService.registerStudent(dto);
        return createResponseEntity(student);
    }

    @PostMapping("/register/general")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO dto) {
        ServiceAnswer user = siptisUserService.registerUser(dto);
        return createResponseEntity(user);
    }

    @GetMapping("/personalInformation/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getInfoById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/personalInformation")
    public ResponseEntity<?> getPersonalInfo(@RequestHeader(name = "Authorization") String token) {

        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userCareer")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> getCareer(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService =
                siptisUserService.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas")
    //@PreAuthorize("hasAuthority('')")
    public ResponseEntity<?> getAreas(@RequestHeader(name = "Authorization") String token) {

        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.getUserAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAreasById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.getUserAreasById(id);
        return createResponseEntity(answerService);
    }


    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/roles/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getRolesById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles")
    public ResponseEntity<?> updateRoles(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody RolesListDTO dto) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateRoles(@PathVariable int userId, @Valid @RequestBody RolesListDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/admins")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAdminList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getAdminUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getStudentList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "STUDENT", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/teachers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getTeacherList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TEACHER", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/tribunals")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getTribunalsList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TRIBUNAL", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/generalUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getNormalUserList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getNormalUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> deleteUser(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.deleteUser(id);
        return createResponseEntity(answer);
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenDTO request) {
        String refreshToken = request.getRefreshToken();
        ServiceAnswer answer = siptisUserService.updateToken(refreshToken);
        return createResponseEntity(answer);
    }


    @GetMapping("/list/potentialTutors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getPotentialTutors(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TUTOR", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/potentialSupervisors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getPotentialSupervisors(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "SUPERVISOR", pageable);
        return createResponseEntity(answerService);
    }

    @PutMapping("/editInformation")
    public ResponseEntity<?> editInformation(
            @RequestHeader(name = "Authorization") String token,
            @Valid @RequestBody UserEditInformationDTO dto) {

        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answer = siptisUserService.userEditPersonalInformation(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/editUserInformation/{userId}")
    public ResponseEntity<?> editUser(
            @PathVariable int userId,
            @Valid @RequestBody AdminEditUserInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.adminEditUserInformation(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/editStudentInformation/{userId}")
    public ResponseEntity<?> editSpecialUser(
            @PathVariable int userId,
            @Valid @RequestBody AdminEditStudentInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.adminEditStudentInformation(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/updateAreas")
    public ResponseEntity<?> updateAreas(
            @RequestHeader(name = "Authorization") String token, @RequestBody UserSelectedAreasDTO dto) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answer = siptisUserService.updateAreas(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/updateAreas/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAreas(@PathVariable int userId, @RequestBody UserSelectedAreasDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.updateAreas(id, dto);
        return createResponseEntity(answer);
    }


    @GetMapping("/userCareer/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getCareer(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                siptisUserService.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/getTribunals")
    ResponseEntity<?> getPossibleTribunals() {
        ServiceAnswer answer = siptisUserService.getPossibleTribunals();
        return createResponseEntity(answer);
    }

    @PostMapping("/register/director/{career}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> registerCareerDirector(@PathVariable String career, @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(id, directorRole);
        return createResponseEntity(answer);
    }

    @GetMapping("/directorInformation/{career}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> getDirectorInfo(@PathVariable String career) {
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserService.getDirectorPersonalInformation(directorRole);
        return createResponseEntity(answer);
    }

    @DeleteMapping("/removeDirector/{career}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> removeDirector(@PathVariable String career) {
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserService.removeDirectorRole(directorRole);
        return createResponseEntity(answer);
    }

    @GetMapping("/studentsInCareer/{careerId}")
    public ResponseEntity<?> getStudentsInCareer(@PathVariable Long careerId) {
        ServiceAnswer answerService =
                siptisUserService.getNumberStudentsCareer(careerId);
        return createResponseEntity(answerService);
    }

    @GetMapping("/studentsByYear/{careerId}")
    public ResponseEntity<?> getNumberOfStudentsByYearAndCareer(@PathVariable Long careerId) {
        ServiceAnswer answerService =
                siptisUserService.getNumberOfStudentsByYearAndCareer(careerId);
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

    @GetMapping("/personal-activities")
    public ResponseEntity<?> getPersonalProjectActivities(
            @RequestHeader(name = "Authorization") String token,
            Pageable pageable) {
        Long idL = siptisUserService.getIdFromToken(token);
        ServiceAnswer answer = siptisUserService.getPersonalActivities(idL, pageable);
        return crearResponseEntityRegistrar(answer);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (messageService == ServiceMessage.OK) {
            httpStatus = HttpStatus.OK;
        }


        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}

