package backend.siptis.model.pjo.vo.projectManagement;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class UserDefenseScheduleVO {
    private Long id;
    private String name;
    private HashMap<String, List<String[]>> schedules;

    public UserDefenseScheduleVO(Long id, String name, HashMap<String, List<String[]>> schedules) {
        this.id = id;
        this.name = name;
        this.schedules = schedules;
    }
}
