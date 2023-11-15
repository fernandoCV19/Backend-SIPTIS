package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entityConstants.UserDataConstants.UserInformationTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = UserInformationTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserInformationTable.Id.NAME,
            nullable = UserInformationTable.Id.NULLABLE,
            unique = UserInformationTable.Id.UNIQUE)
    private Long id;

    @Column(name = UserInformationTable.Names.NAME)
    private String names;

    @Column(name = UserInformationTable.LastNames.NAME)
    private String lastNames;

    @Column(name = UserInformationTable.FullName.NAME)
    private String fullName;

    @Column(name = UserInformationTable.CellNumber.NAME)
    private String celNumber;

    @Column(name = UserInformationTable.CI.NAME)
    private String ci;

    @Column(name = UserInformationTable.BirthDate.NAME)
    private Date birthDate;

    @Column(name = UserInformationTable.CodSIS.NAME)
    private String codSIS;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserInformationTable.JoinSiptisUser.NAME)
    private SiptisUser siptisUser;
}
