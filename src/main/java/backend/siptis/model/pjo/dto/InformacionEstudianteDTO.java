package backend.siptis.model.pjo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformacionEstudianteDTO {

    private String email;
    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private Date fechaNac;
    private String codSIS;
}
