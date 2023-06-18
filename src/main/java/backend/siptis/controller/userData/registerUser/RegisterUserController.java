package backend.siptis.controller.userData.registerUser;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;
import backend.siptis.service.userData.registerUser.RegisterUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class RegisterUserController {

    @Autowired
    private final RegisterUser registerUserService;


    @PostMapping("/register/student")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegisterDTO studentDTO){

        ServiceAnswer student = registerUserService.registerStudent(studentDTO);
        return createResponse(student);
    }

    @PostMapping("/register/teacher")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerTeacher(
            @RequestBody TeacherRegisterDTO teacherDTO){

        ServiceAnswer teacher = registerUserService.registerTeacher(teacherDTO);
        return createResponse(teacher);
    }

    @PostMapping("/register/admin")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(
            @RequestBody AdminRegisterDTO adminRegisterDTO){

        ServiceAnswer admin = registerUserService.registerAdmin(adminRegisterDTO);
        return createResponse(admin);

    }


    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
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
