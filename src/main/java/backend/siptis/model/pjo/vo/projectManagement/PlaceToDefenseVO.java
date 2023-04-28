package backend.siptis.model.pjo.vo.projectManagement;

import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import lombok.Data;

@Data
public class PlaceToDefenseVO {

    private Long id;
    private String name;
    private String location;
    private Integer capacity;

    public PlaceToDefenseVO(PlaceToDefense placeToDefense) {
        this.id = placeToDefense.getId();
        this.name = placeToDefense.getName();
        this.location = placeToDefense.getLocation();
        this.capacity = placeToDefense.getCapacity();
    }
}
