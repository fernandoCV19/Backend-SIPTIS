package backend.siptis.model.pjo.dto.stadisticsDTO;

import backend.siptis.model.entity.userData.UserArea;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserTribunalDTO {
    private Long id;
    private String names;
    private String lastnames;
    private Set<UserArea> areas;
}
