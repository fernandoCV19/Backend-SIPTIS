package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceReservedByMonthVO {
    private Map<LocalDate, List<DefenseVO>> reservedSchedulesByDay;

    public PlaceReservedByMonthVO(List<Defense> defenses, Integer month) {
        this.reservedSchedulesByDay = orderDefensesByDay(defenses, month);
    }

    private Map<LocalDate, List<DefenseVO>> orderDefensesByDay(List<Defense> defenses, Integer month) {
        Map<LocalDate, List<DefenseVO>> res = new HashMap<>();
        Integer year = LocalDate.now().getYear();
        LocalDate date = LocalDate.of(year, month, 1);

        while (date.getMonthValue() == month) {
            res.put(date, filterDefensesByDay(defenses, date.getDayOfMonth()));
            date = date.plusDays(1);
        }

        return res;
    }

    private List<DefenseVO> filterDefensesByDay(List<Defense> defenses, int dayOfMonth) {
        return defenses.
                stream().
                filter(defense -> defense.getDate().getDayOfMonth() == dayOfMonth).
                map(DefenseVO::new).
                toList();
    }
}
