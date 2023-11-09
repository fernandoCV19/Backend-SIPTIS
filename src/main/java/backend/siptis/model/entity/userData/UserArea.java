package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "user_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String name;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "areas")
    @JsonBackReference
    private Collection<SiptisUser> siptisUsers;
}
