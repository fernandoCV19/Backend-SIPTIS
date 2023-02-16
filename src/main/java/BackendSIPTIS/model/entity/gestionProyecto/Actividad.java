package BackendSIPTIS.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "actividad")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;
    @Column(name = "fecha_actividad")
    private Date fechaActividad;
    @Column(name = "recordatorio_wpp")
    private String recordatorioWpp;

    @ManyToOne
    @JoinColumn(name = "id_proyecto_grado", nullable = false)
    private ProyectoGrado proyectoGrado;
}
