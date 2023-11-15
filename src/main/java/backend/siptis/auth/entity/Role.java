package backend.siptis.auth.entity;

import backend.siptis.utils.constant.entityConstants.AuthConstants.RoleTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = RoleTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = RoleTable.Id.NAME,
            nullable = RoleTable.Id.NULLABLE,
            unique = RoleTable.Id.UNIQUE)
    private Integer id;

    @Column(name = RoleTable.Name.NAME)
    private String name;

    @ManyToMany(mappedBy = RoleTable.MappedSiptisUsers.NAME, fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<SiptisUser> siptisUsers;

    public Role(String rolName) {
        this.name = rolName;
    }


    public String toString() {
        return name;
    }
}
