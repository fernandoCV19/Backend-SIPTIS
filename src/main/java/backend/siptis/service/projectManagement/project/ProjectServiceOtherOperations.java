package backend.siptis.service.projectManagement.project;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.userData.Schedule;
import backend.siptis.model.pjo.vo.projectManagement.InfoToCreateADefenseVO;
import backend.siptis.model.pjo.vo.projectManagement.InvolvedPeopleVO;
import backend.siptis.model.pjo.vo.projectManagement.UserDefenseScheduleVO;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceOtherOperations {

    private final ProjectRepository projectRepository;

    public ServiceAnswer getPresentations(Long projectId) {
        Optional<Project> oProyectoGrado = projectRepository.findById(projectId);
        if (oProyectoGrado.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project proyecto = oProyectoGrado.get();
        List<Presentation> presentaciones = proyecto.getPresentations().stream().toList();
        if (presentaciones.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PRESENTATIONS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }

    public ServiceAnswer getInvolvedPeople(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if (query.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new InvolvedPeopleVO(project)).build();
    }

    public ServiceAnswer getSchedulesInfoToAssignADefense(Long projectId) {
        Optional<Project> query = projectRepository.findById(projectId);
        if (query.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();
        Collection<ProjectStudent> students = project.getStudents();
        Collection<ProjectTribunal> tribunals = project.getTribunals();
        List<SiptisUser> studentsList = students.stream().map(ProjectStudent::getStudent).toList();
        List<SiptisUser> tribunalsList = tribunals.stream().map(ProjectTribunal::getTribunal).toList();
        List<UserDefenseScheduleVO> studentsDefenseInfo = studentsList.stream().map(this::createDefenseInfo).toList();
        List<UserDefenseScheduleVO> tribunalsDefenseInfo = tribunalsList.stream().map(this::createDefenseInfo).toList();
        InfoToCreateADefenseVO data = new InfoToCreateADefenseVO(studentsDefenseInfo, tribunalsDefenseInfo);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    private UserDefenseScheduleVO createDefenseInfo(SiptisUser student) {
        HashMap<String, List<String[]>> schedules = new HashMap<>();
        for (Schedule schedule : student.getAvailableSchedules()) {
            String day = schedule.getDay();
            String start = schedule.getHourStart();
            String finish = schedule.getHourFinish();
            if (!schedules.containsKey(day)) {
                schedules.put(day, new ArrayList<>());
            }
            List<String[]> aux = schedules.get(day);
            aux.add(new String[]{start, finish});
        }
        return new UserDefenseScheduleVO(student.getId(), student.getUserInformation().getNames() + " " + student.getUserInformation().getLastNames(), schedules);
    }
}
