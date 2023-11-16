package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entityConstants.UserDataConstants.DocumentTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = DocumentTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DocumentTable.Id.NAME,
            nullable = DocumentTable.Id.NULLABLE,
            unique = DocumentTable.Id.UNIQUE)
    private Long id;

    @Column(name = DocumentTable.Path.NAME)
    private String path;

    @Column(name = DocumentTable.Type.NAME)
    private String type;

    @Column(name = DocumentTable.Description.NAME)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DocumentTable.JoinSiptisUser.NAME,
            nullable = DocumentTable.JoinSiptisUser.NULLABLE)
    @JsonBackReference
    private SiptisUser siptisUser;

    private LocalDateTime date;
}
