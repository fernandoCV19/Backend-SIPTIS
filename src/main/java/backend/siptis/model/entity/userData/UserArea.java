package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "user_area")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class
UserArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String name;


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "areas")
    private Collection<SiptisUser> siptisUsers;
}
