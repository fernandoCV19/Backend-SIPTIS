package backend.siptis.model.pjo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistrarEstudianteDTO {
    private String email;
    private String contrasena;
    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private Date fechaNac;
    private String codSIS;
    private String carrera;
}
