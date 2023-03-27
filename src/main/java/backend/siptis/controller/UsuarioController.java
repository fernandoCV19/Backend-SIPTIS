package backend.siptis.controller;

import backend.siptis.service.datosUsuario.UserAuthService;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final UserAuthService userAuthService;




    @PostMapping("/register/student")
    public StudentInformationDTO registerStudent(
            @RequestBody StudentRegisterDTO estudianteDTO){

        StudentInformationDTO estudiante = userAuthService.registerStudent(estudianteDTO);
        return estudiante;
    }
}
