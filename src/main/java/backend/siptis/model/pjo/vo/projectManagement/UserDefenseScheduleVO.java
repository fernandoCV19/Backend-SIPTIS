package backend.siptis.model.pjo.vo.projectManagement;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDefenseScheduleVO {
    private Long id;
    private String name;
    private Map<String, List<String[]>> schedules;

    public UserDefenseScheduleVO(Long id, String name, Map<String, List<String[]>> schedules) {
        this.id = id;
        this.name = name;
        this.schedules = schedules;
    }
}
