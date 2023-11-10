package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private String fullname;
    private String celNumber;
    private String ci;
    private Date birthDate;
    private String codSIS;
    //private boolean wppMessages = Boolean.FALSE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SiptisUser siptisUser;
}
