package backend.siptis.model.pjo.dto.projectManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceAvailabilityByMonth {
    private HashMap<Integer, List<Date>> availableSchedulesByDay;


}
