package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String day;
    private String hour;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiptisUser siptisUser;
}
