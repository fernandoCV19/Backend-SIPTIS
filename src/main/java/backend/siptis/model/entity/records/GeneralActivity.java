package backend.siptis.model.entity.records;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "actividad_general")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;
    @Column(name = "fecha_actividad")
    private Date fechaActividad;
}
