package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.authentication.RefreshTokenDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.service.userData.RefreshTokenService;
import backend.siptis.service.userData.SiptisUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class SiptisUserController {

    private final SiptisUserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LogInDTO logInDTO) {
        ServiceAnswer answerService = userService.logIn(logInDTO);
        return createResponseEntity(answerService);
    }


    @PostMapping("/register/teacher")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerTeacher(
            @RequestBody RegisterUserDTO dto){

        ServiceAnswer student = userService.registerTeacher(dto);
        return createResponseEntity(student);
    }

    @PostMapping("/register/admin")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(
            @RequestBody AdminRegisterDTO dto){

        ServiceAnswer student = userService.registerAdmin(dto);
        return createResponseEntity(student);

    }

    @PostMapping("/register/student")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(
            @RequestBody RegisterStudentDTO dto){

        ServiceAnswer student = userService.registerStudent(dto);
        return createResponseEntity(student);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenDTO request) {
        String refreshToken = request.getRefreshToken();
        System.out.println("ACTUALIZANDO TOKEN");
        ServiceAnswer answer = userService.updateToken(refreshToken);
        return createResponseEntity(answer);
    }

    @GetMapping("/getAllToken")
    public ResponseEntity<?> getAllToken() {
        ServiceAnswer answer = refreshTokenService.getAllToken();
        return createResponseEntity(answer);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = userService.getUserById(id);
        return createResponseEntity(answer);
    }

    @GetMapping("/getUserEmail/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        // Long id = Long.valueOf(userId);
        ServiceAnswer answer = userService.getUserByEmail(email);
        return createResponseEntity(answer);
    }

    @GetMapping("/getToken/{userId}")
    public ResponseEntity<?> getToken(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = refreshTokenService.getToken(id);
        return createResponseEntity(answer);
    }

    @GetMapping("/getTokenS/{token}")
    public ResponseEntity<?> getTokenWithString(@PathVariable String token) {
        //Long id = Long.valueOf(userId);
        ServiceAnswer answer = refreshTokenService.getToken(token);
        return createResponseEntity(answer);
    }


    @GetMapping("/list/students")
    public ResponseEntity<?> getStudentList(String search, Pageable pageable){
        /*ServiceAnswer answerService =
                refreshTokenService.getAllToken();*/
        ServiceAnswer answerService =
                userService.getStudentList(search, pageable);

        return createResponseEntity(answerService);
    }

    @GetMapping("/list/teachers")
    public ResponseEntity<?> getTeacherList(String search, Pageable pageable){
        ServiceAnswer answerService =
                userService.getTeacherList(search, pageable);

        return createResponseEntity(answerService);
    }

    @GetMapping("/list/admins")
    public ResponseEntity<?> getAdminList(Pageable pageable){
        ServiceAnswer answerService =
                userService.getAdminList(pageable);

        return createResponseEntity(answerService);
    }

    @GetMapping("/personalInformation/{userId}")
    public ResponseEntity<?> getInfoById(@PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                userService.getUserPersonalInformation(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/personalInformation")
    public ResponseEntity<?> getPersonalInfo(@RequestHeader (name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService =
                userService.getUserPersonalInformation(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreas(@RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService = userService.getTeacherAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas/{userId}")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreasById(@PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = userService.getTeacherAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userNotSelectedAreas")
    public ResponseEntity<?> getNotSelectedAreas(
            @RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService =
                userService.getTeacherNotSelectedAreasById(id);

        return createResponseEntity(answerService);
    }

    @PostMapping("/updateAreas")
    public ResponseEntity<?> updateAreas(
            @RequestHeader(name="Authorization") String token,
            @RequestBody UserSelectedAreasDTO dto) {

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answer = userService.updateAreas(id, dto);
        return createResponseEntity(answer);
    }

    @GetMapping("/userCareer")
    public ResponseEntity<?> getCareer(@RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService =
                userService.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/userCareer/{userId}")
    public ResponseEntity<?> getCareer(@PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                userService.getStudentCareerById(id);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/delete/{userId}")
    ResponseEntity<?> deleteUser (@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = userService.deleteUser(id);
        return createResponseEntity(answer);
    }



    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK || messageService == ServiceMessage.USER_DELETED){
            httpStatus = HttpStatus.OK;
        }

        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
    /*

    @GetMapping("/userCareerById/{userId}")
    public ResponseEntity<?> getCareer(
            @PathVariable int userId){
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                userService.getStudentCareerById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreasById/{userId}")
    public ResponseEntity<?> getAreas(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = userService.getTeacherAreasById(id);

        return createResponseEntity(answerService);
    }



    @GetMapping("/userNotSelectedAreasById/{userId}")
    public ResponseEntity<?> getNotSelectedAreasById(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                userService.getTeacherNotSelectedAreasById(id);

        return createResponseEntity(answerService);
    }



/*


    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll() {
        ServiceAnswer answerService = userService.findAll();
        return crearResponseEntityRegistrar(answerService);
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<?> editUser(
            @PathVariable int userId,
            @RequestBody EditStudentInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = adminEditInformationService.editStudentInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/editTeacher/{userId}")
    public ResponseEntity<?> editTeacher(
            @PathVariable int userId,
            @RequestBody EditTeacherInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = adminEditInformationService.editTeacherInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }


    @PostMapping("/editTeacher")
    public ResponseEntity<?> editMiInformationTeacher(
            @RequestBody TeacherEditPersonalInfoDTO dto,
            @RequestHeader(name = "Authorization") String token) {

        Long id = userService.getIdFromToken(token);

        ServiceAnswer answer = userEditInformationService.teacherEditPersonalInfo(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    */
}