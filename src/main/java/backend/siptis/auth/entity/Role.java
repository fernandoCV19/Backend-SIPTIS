package backend.siptis.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    private String rolName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Collection<SiptisUser> siptisUsers;

    public Role(String rolName){
        this.rolName = rolName;
    }

    public Role(Integer id) {
        super();
        this.id = id;
    }

    public String toString(){
        return rolName;
    }
}
