package backend.siptis.model.entity.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "review")
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private SiptisUser siptisUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", nullable = false)
    @JsonBackReference
    private Presentation presentation;
    private Date date;

    public Review(String documentPath, String commentary, SiptisUser siptisUser, Presentation presentation, Date date) {
        this.documentPath = documentPath;
        this.commentary = commentary;
        this.siptisUser = siptisUser;
        this.presentation = presentation;
        this.date = date;
    }
}
