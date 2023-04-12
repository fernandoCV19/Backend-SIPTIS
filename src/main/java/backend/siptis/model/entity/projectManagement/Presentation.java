package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "presentation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "blue_book_path")
    private String blueBookPath;

    @Column(name = "project_path")
    private String projectPath;

    private String phase;

    private Boolean reviewed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = "presentation",  fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Review> reviews;

    private Date date;
}
