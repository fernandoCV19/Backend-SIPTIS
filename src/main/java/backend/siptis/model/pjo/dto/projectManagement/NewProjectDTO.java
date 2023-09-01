package backend.siptis.model.pjo.dto.projectManagement;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class NewProjectDTO {

    @NotEmpty(message = "El nombre no puede ser vacio.")
    @Size(min = 2, message = "El nombre tiene que ser mayor a 1 caracter")
    @Size(max = 50, message = "El nombre tiene que ser menor a 100 caracteres")
    private String name;
    @NotNull(message = "No puede crear el proyecto sin una modalidad asignada   .")
    private Long modalityId;

    private String perfilPath;

    @NotNull(message = "No puede crear el proyecto sin estudiantes asignados.")
    @NotEmpty(message = "No puede crear el proyecto sin estudiantes asignados.")
    private ArrayList<Long> studentsId;
    @NotEmpty(message = "No puede crear el proyecto sin tutores asignados.")
    private ArrayList<Long> tutorsId;
}
