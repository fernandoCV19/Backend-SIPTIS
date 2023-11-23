package backend.siptis.model.pjo.vo.project_management;

import backend.siptis.model.entity.defense_management.Defense;
import backend.siptis.model.entity.defense_management.PlaceToDefense;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Data
public class PlaceWithReservedDateVO {

    private String name;
    private String capacity;
    private String location;
    private List<LocalDate> reservations;

    public PlaceWithReservedDateVO(PlaceToDefense place) {
        name = place.getName();
        capacity = place.getCapacity().toString();
        location = place.getLocation();
        reservations = place.getDefenses().stream().map(Defense::getDate).sorted(Comparator.naturalOrder()).toList();
    }
}
