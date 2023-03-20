package backend.siptis.controller;

import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @PostMapping("/registrar/estudiante")
    public String registrarEstudiante(
            @RequestBody RegistrarEstudianteDTO estudianteDTO){

        return estudianteDTO.getEmail();
    }
}
