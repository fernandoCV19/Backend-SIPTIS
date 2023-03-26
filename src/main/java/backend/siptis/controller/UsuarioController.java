package backend.siptis.controller;

import backend.siptis.auth.service.InformacionUsuarioService;
import backend.siptis.auth.service.UsuarioAuthService;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioAuthService usuarioAuthService;




    @PostMapping("/registrar/estudiante")
    public InformacionEstudianteDTO registrarEstudiante(
            @RequestBody RegistrarEstudianteDTO estudianteDTO){

        InformacionEstudianteDTO estudiante = usuarioAuthService.registrarEstudiante(estudianteDTO);
        return estudiante;
    }
}
