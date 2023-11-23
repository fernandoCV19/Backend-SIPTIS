package backend.siptis.model.entity.user_data;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entity_constants.UserDataConstants.UserAreaTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = UserAreaTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserAreaTable.Id.NAME,
            nullable = UserAreaTable.Id.NULLABLE,
            unique = UserAreaTable.Id.UNIQUE)
    private Long id;

    @Column(name = UserAreaTable.Name.NAME)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = UserAreaTable.MappedSiptisUsers.NAME)
    @JsonBackReference
    private Collection<SiptisUser> siptisUsers;
}
