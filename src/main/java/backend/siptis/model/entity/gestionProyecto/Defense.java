package backend.siptis.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "defensa")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "lugar_defensa")
    private String place;

    @Column(name = "hora_defensa")
    private String hour;

    @OneToOne
    private ProyectoGrado project;
}
