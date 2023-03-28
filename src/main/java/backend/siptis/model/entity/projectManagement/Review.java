package backend.siptis.model.entity.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "document_path")
    private String documentPath;
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiptisUser siptisUser;

    @ManyToOne
    @JoinColumn(name = "presentation_id", nullable = false)
    private Presentation presentation;
}
