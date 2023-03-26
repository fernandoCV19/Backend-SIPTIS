package backend.siptis.model.entity.records;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
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
public class Activity {
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
    @JoinColumn(name = "proyecto_grado_id", nullable = false)
    private ProyectoGrado proyectoGrado;
}
