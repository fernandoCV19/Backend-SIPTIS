package backend.siptis.model.entity.presentations;

import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.PresentationsConstants.PresentationTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = PresentationTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PresentationTable.Id.NAME,
            nullable = PresentationTable.Id.NULLABLE,
            unique = PresentationTable.Id.UNIQUE)
    private Long id;

    @Column(name = PresentationTable.BlueBookPath.NAME)
    private String blueBookPath;

    @Column(name = PresentationTable.ProjectPath.NAME)
    private String projectPath;

    @Column(name = PresentationTable.Phase.NAME)
    private String phase;

    @Column(name = PresentationTable.Reviewed.NAME)
    private Boolean reviewed = false;

    @Column(name = PresentationTable.Date.NAME)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = PresentationTable.JoinProject.NAME,
            nullable = PresentationTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = PresentationTable.MappedReviews.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Review> reviews;
}
