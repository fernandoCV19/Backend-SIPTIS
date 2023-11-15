package backend.siptis.model.entity.presentations;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entityConstants.PresentationsConstants.ReviewTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = ReviewTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ReviewTable.Id.NAME,
            nullable = ReviewTable.Id.NULLABLE,
            unique = ReviewTable.Id.UNIQUE)
    private Long id;

    @Column(name = ReviewTable.DocumentPath.NAME)
    private String documentPath;

    @Column(name = ReviewTable.Commentary.NAME)
    private String commentary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ReviewTable.JoinSiptisUser.NAME,
            nullable = ReviewTable.JoinSiptisUser.NULLABLE)
    @JsonBackReference
    private SiptisUser siptisUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ReviewTable.JoinPresentation.NAME,
            nullable = ReviewTable.JoinPresentation.NULLABLE)
    @JsonBackReference
    private Presentation presentation;

    @Column(name = ReviewTable.Date.NAME)
    private LocalDateTime date;

    public Review(String documentPath, String commentary, SiptisUser siptisUser, Presentation presentation, LocalDateTime date) {
        this.documentPath = documentPath;
        this.commentary = commentary;
        this.siptisUser = siptisUser;
        this.presentation = presentation;
        this.date = date;
    }
}
