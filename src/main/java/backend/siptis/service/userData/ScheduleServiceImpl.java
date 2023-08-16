package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.userData.AllSchedulesVO;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ProjectRepository projectRepository;


    @Override
    public ServiceAnswer getAllSchedulesFromAProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(projectOptional.isEmpty()){
            return new ServiceAnswer(ServiceMessage.ID_DOES_NOT_EXIST, null);
        }
        Project project = projectOptional.get();
        Collection<ProjectStudent> students = project.getStudents();
        Collection<ProjectTribunal> tribunals = project.getTribunals();
        AllSchedulesVO data = new AllSchedulesVO(students, tribunals);
        return new ServiceAnswer(ServiceMessage.OK, data);
    }
}
