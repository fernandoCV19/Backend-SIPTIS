package backend.siptis.auth.entity;

import backend.siptis.utils.constant.entityConstants.AuthConstants.RefreshTokenTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = RefreshTokenTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = RefreshTokenTable.Id.NAME,
            unique = RefreshTokenTable.Id.UNIQUE,
            nullable = RefreshTokenTable.Id.NULLABLE)
    private Long id;

    @OneToOne
    @JoinColumn(name = RefreshTokenTable.JoinSiptisUser.NAME,
            referencedColumnName = RefreshTokenTable.JoinSiptisUser.REFERENCED_COLUMN)
    private SiptisUser siptisUser;

    @Column(name = RefreshTokenTable.Token.NAME,
            nullable = RefreshTokenTable.Token.NULLABLE,
            unique = RefreshTokenTable.Token.UNIQUE)
    private String token;

    @Column(nullable = RefreshTokenTable.ExpireDate.NULLABLE,
            name = RefreshTokenTable.ExpireDate.NAME)
    private Instant expireDate;
}
