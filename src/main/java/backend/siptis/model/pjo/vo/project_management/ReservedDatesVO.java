package backend.siptis.model.pjo.vo.project_management;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class ReservedDatesVO {

    private HashMap<String, List<Date>> reservedDates;
}
