package backend.siptis.model.pjo.vo.project_management;

import lombok.Data;

import java.util.List;

@Data
public class InfoToCreateADefenseVO {
    List<UserDefenseScheduleVO> students;
    List<UserDefenseScheduleVO> tribunals;

    public InfoToCreateADefenseVO(List<UserDefenseScheduleVO> studentsDefenseInfo, List<UserDefenseScheduleVO> tribunalsDefenseInfo) {
        this.students = studentsDefenseInfo;
        this.tribunals = tribunalsDefenseInfo;
    }
}
