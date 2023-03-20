package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoGradoMenuPrincipalDTO {
    private Long idProyecto;
    private String nombreProyecto;
    private List<String> tutores;
    private List<String> docentes;
    private List<String> estudiantes;
    private List<String> supervisores;
    private String masInformacion;
    private List<String> areas;
    private List<String> subAreas;
    private Boolean aceptado;
    private Boolean revisado;


    public ProyectoGradoMenuPrincipalDTO(ProyectoGrado proyectoGrado, Boolean aceptado, Boolean revisado) {
        /*idProyecto = proyectoGrado.getId();
        nombreProyecto = proyectoGrado.getNombre();
        tutores = proyectoGrado.getTutores()
                .stream()
                .map(aux -> aux.getTutor().getNombres())
                .toList();
        docentes = proyectoGrado.getDocentes()
                .stream()
                .map(aux -> aux.getDocente().getNombres())
                .toList();
        estudiantes = proyectoGrado.getEstudiantes()
                .stream()
                .map(aux -> aux.getEstudiante().getNombres())
                .toList();
        supervisores = proyectoGrado.getSupervisores()
                .stream()
                .map(aux -> aux.getSupervisor().getNombres())
                .toList();
        masInformacion = "";
        areas = proyectoGrado.getAreas()
                .stream()
                .map(aux -> aux.getNombre())
                .toList();
        subAreas = proyectoGrado.getSubAreas()
                .stream()
                .map(aux -> aux.getNombre())
                .toList();*/
    }
}
