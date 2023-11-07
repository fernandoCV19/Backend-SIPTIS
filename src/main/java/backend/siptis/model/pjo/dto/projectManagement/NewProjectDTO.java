package backend.siptis.model.pjo.dto.projectManagement;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<Long> studentsId;
    @NotNull(message = "No puede crear el proyecto sin tutores asignados.")
    @NotEmpty(message = "No puede crear el proyecto sin tutores asignados.")
    private List<Long> tutorsId;
    @NotNull(message = "No puede crear el proyecto sin docente de la materia asignados.")
    @NotEmpty(message = "No puede crear el proyecto sin docente de la materia asignados.")
    private List<Long> teachersId;
    @NotNull(message = "No puede crear el proyecto sin areas asignadas.")
    @NotEmpty(message = "No puede crear el proyecto sin areas asignadas.")
    private List<Long> areasId;
    @NotNull(message = "No puede crear el proyecto sin subareas asignadas.")
    @NotEmpty(message = "No puede crear el proyecto sin subareas asignadas.")
    private List<Long> subAreasId;
}
