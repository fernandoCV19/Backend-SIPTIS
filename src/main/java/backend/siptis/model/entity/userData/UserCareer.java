package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "user_career")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String name;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SiptisUser siptisUser;*/

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "career")
    private Collection<SiptisUser> siptisUsers;

    public String toString(){
        return name;
    }
}
