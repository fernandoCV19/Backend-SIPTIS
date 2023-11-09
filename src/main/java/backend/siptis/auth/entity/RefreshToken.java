package backend.siptis.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "siptis_user_id", referencedColumnName = "id")
    private SiptisUser siptisUser;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false,name = "expire_date")
    private Instant expireDate;
}
