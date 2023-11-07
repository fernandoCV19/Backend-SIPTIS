package backend.siptis.model.pjo.dto.userDataDTO;

import backend.siptis.model.entity.userData.UserArea;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSelectedAreasDTO {
    private List<Long> ids;
}
