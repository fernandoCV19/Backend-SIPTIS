package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String path;
    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiptisUser siptisUser;
}
