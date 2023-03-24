package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String names;
    private String lastnames;
    private String celNumber;
    private String ci;
    //private Date fechaNac;
    private String codSIS;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SiptisUser siptisUser;
}
