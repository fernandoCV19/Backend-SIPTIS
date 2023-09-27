package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
