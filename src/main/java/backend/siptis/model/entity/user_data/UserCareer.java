package backend.siptis.model.entity.user_data;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entity_constants.UserDataConstants.UserCareerTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = UserCareerTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserCareerTable.Id.NAME,
            nullable = UserCareerTable.Id.NULLABLE,
            unique = UserCareerTable.Id.UNIQUE)
    private Long id;

    @Column(name = UserCareerTable.Name.NAME)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = UserCareerTable.MappedSiptisUsers.NAME)
    @JsonBackReference
    private Collection<SiptisUser> siptisUsers;

    public String toString() {
        return name;
    }
}
