package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.authentication.RefreshTokenDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.*;
import backend.siptis.service.userData.RefreshTokenService;
import backend.siptis.service.userData.SiptisUserService;
import jakarta.validation.Valid;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class SiptisUserController {

    private final SiptisUserService siptisUserService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LogInDTO logInDTO) {
        ServiceAnswer answerService = siptisUserService.logIn(logInDTO);
        return createResponseEntity(answerService);
    }

    @PostMapping("/register/admin")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterAdminDTO dto) {
        ServiceAnswer admin = siptisUserService.registerAdmin(dto);
        return createResponseEntity(admin);
    }

    @PostMapping("/register/student")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody RegisterStudentDTO dto) {
        ServiceAnswer student = siptisUserService.registerStudent(dto);
        return createResponseEntity(student);
    }

    @PostMapping("/register/general")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO dto ) {
        ServiceAnswer user = siptisUserService.registerUser(dto);
        return createResponseEntity(user);
    }

    @GetMapping("/personalInformation/{userId}")
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
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreas(@RequestHeader(name = "Authorization") String token) {

        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.getUserAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas/{userId}")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreasById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.getUserAreasById(id);
        return createResponseEntity(answerService);
    }


    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.getRoles(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/roles/{userId}")
    public ResponseEntity<?> getRolesById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.getRoles(id);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles")
    public ResponseEntity<?> updateRoles(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody RolesListDTO dto) {
        Long id = siptisUserService.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserService.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles/{userId}")
    public ResponseEntity<?> updateRoles(@PathVariable int userId, @Valid @RequestBody RolesListDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserService.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/students")
    public ResponseEntity<?> getStudentList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "STUDENT", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/teachers")
    public ResponseEntity<?> getTeacherList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TEACHER", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/tribunals")
    public ResponseEntity<?> getTribunalsList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TRIBUNAL", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/generalUsers")
    public ResponseEntity<?> getNormalUserList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getNormalUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/delete/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.deleteUser(id);
        return createResponseEntity(answer);
    }







    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenDTO request) {
        String refreshToken = request.getRefreshToken();
        System.out.println("ACTUALIZANDO TOKEN");
        ServiceAnswer answer = siptisUserService.updateToken(refreshToken);
        return createResponseEntity(answer);
    }



    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.getUserById(id);
        return createResponseEntity(answer);
    }

    @GetMapping("/getUserEmail/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        // Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.getUserByEmail(email);
        return createResponseEntity(answer);
    }

    @GetMapping("/getToken/{userId}")
    public ResponseEntity<?> getToken(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = refreshTokenService.getToken(id);
        return createResponseEntity(answer);
    }

    @GetMapping("/list/potentialTutors")
    public ResponseEntity<?> getPotentialTutors(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "TUTOR", pageable);
        return createResponseEntity(answerService);
    }

    @GetMapping("/list/admins")
    public ResponseEntity<?> getAdminList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserService.getUserList(search, "ADMIN", pageable);
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
            @Valid @RequestBody UniversityUserPersonalInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.adminEditUserPersonalInformation(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/editSpecialUserInformation/{userId}")
    public ResponseEntity<?> editSpecialUser(
            @PathVariable int userId,
            @Valid @RequestBody GeneralUserPersonalInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.adminEditSpecialUserPersonalInformation(id, dto);
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
    public ResponseEntity<?> updateAreas(@PathVariable int userId, @RequestBody UserSelectedAreasDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.updateAreas(id, dto);
        return createResponseEntity(answer);
    }


    @GetMapping("/userCareer/{userId}")
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

    @PostMapping("/register/director/informatica/{userId}")
    ResponseEntity<?> registerCareerDirector(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(id, "INF_DIRECTOR");
        return createResponseEntity(answer);
    }

    @GetMapping("/directorInformation/informatica")
    ResponseEntity<?> getDirectorInfo() {
        ServiceAnswer answer = siptisUserService.getDirectorPersonalInformation("INF_DIRECTOR");
        return createResponseEntity(answer);
    }

    @DeleteMapping("/removeDirector/informatica")
    ResponseEntity<?> removeDirector() {
        ServiceAnswer answer = siptisUserService.removeDirectorRole("INF_DIRECTOR");
        return createResponseEntity(answer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (messageService == ServiceMessage.OK || messageService == ServiceMessage.USER_DELETED) {
            httpStatus = HttpStatus.OK;
        }

        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

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
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}

