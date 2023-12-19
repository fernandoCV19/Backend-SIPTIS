package backend.siptis.service.user_data.document;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defense_management.Defense;
import backend.siptis.model.entity.editors_and_reviewers.*;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.repository.editors_and_reviewers.*;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceCareerDirectorOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentValidationServiceImpl implements DocumentValidationService{

    private final ProjectRepository projectRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final ProjectTutorRepository projectTutorRepository;
    private final ProjectTeacherRepository projectTeacherRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final ProjectStudentRepository projectStudentRepository;
    private final SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    @Override
    public ServiceAnswer studentLetterValidation(LetterGenerationRequestDTO dto){
        Optional<Project> oProject = projectRepository.findById(dto.getProjectId());
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        ProjectStudent student = projectStudentRepository.findByStudentIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (student == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.STUDENT_NOT_FOUND).data(null).build();
        ServiceAnswer answer;
        answer = checkStudentInformation(student.getStudent());
        if(answer != null)
            return answer;
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }
    @Override
    public ServiceAnswer tutorLetterValidation(LetterGenerationRequestDTO dto){
        Optional<Project> oProject = projectRepository.findById(dto.getProjectId());
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        Project project = oProject.get();
        ProjectTutor tutor = projectTutorRepository.findByTutorIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (tutor == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.REVIEWER_NOT_FOUND).data(null).build();
        if(tutor.getAccepted() == null || !tutor.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        Collection<ProjectStudent> students = project.getStudents();
        ServiceAnswer answer;
        for (ProjectStudent student: students){
            answer = checkStudentInformation(student.getStudent());
            if(answer != null)
                return answer;
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }
    @Override
    public ServiceAnswer supervisorLetterValidation(LetterGenerationRequestDTO dto){
        Optional<Project> oProject = projectRepository.findById(dto.getProjectId());
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        Project project = oProject.get();
        ProjectSupervisor supervisor = projectSupervisorRepository.findBySupervisorIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (supervisor == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.REVIEWER_NOT_FOUND).data(null).build();
        if(supervisor.getAccepted() == null || !supervisor.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        Collection<ProjectStudent> students = project.getStudents();
        ServiceAnswer answer;
        for (ProjectStudent student: students){
            answer = checkStudentInformation(student.getStudent());
            if(answer != null)
                return answer;
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }

    @Override
    public ServiceAnswer teacherLetterValidation(LetterGenerationRequestDTO dto) {
        Optional<Project> oProject = projectRepository.findById(dto.getProjectId());
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        Project project = oProject.get();
        ProjectTeacher teacher = projectTeacherRepository.findByTeacherIdAndProjectId(dto.getUserId(), dto.getProjectId());
        if (teacher == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.REVIEWER_NOT_FOUND).data(null).build();
        if(teacher.getAccepted() == null || !teacher.getAccepted() )
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        Collection<ProjectStudent> students = project.getStudents();
        ServiceAnswer answer;
        for (ProjectStudent student: students){
            answer = checkStudentInformation(student.getStudent());
            if(answer != null)
                return answer;
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }

    @Override
    public ServiceAnswer tribunalLetterValidation(LetterGenerationRequestDTO dto){
        Optional<Project> oProject = projectRepository.findById(dto.getProjectId());
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        Project project = oProject.get();
        ProjectTribunal tribunal = projectTribunalRepository.findByProject_IdAndTribunal_Id(dto.getProjectId(), dto.getUserId());
        if (tribunal == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.REVIEWER_NOT_FOUND).data(null).build();
        if(tribunal.getAccepted() == null || !tribunal.getAccepted())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_APPROVED).data(null).build();
        Collection<ProjectStudent> students = project.getStudents();
        ServiceAnswer answer;
        for (ProjectStudent student: students){
            answer = checkStudentInformation(student.getStudent());
            if(answer != null)
                return answer;
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }

    @Override
    public ServiceAnswer defenseActValidation(Long projectID) {
        Optional<Project> oProject = projectRepository.findById(projectID);
        if(oProject.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NOT_FOUND).data(null).build();
        Project project = oProject.get();
        Defense defense = project.getDefense();
        if (defense == null )
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.DEFENSE_NOT_FOUND).data(null).build();
        if (project.getTotalDefensePoints() == null )
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_DEFENSE_POINTS).data(null).build();
        if (project.getTribunals() == null || project.getTribunals().isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.REVIEWER_NOT_FOUND).data(null).build();
        Collection<ProjectStudent> students = project.getStudents();
        ServiceAnswer answer;
        for (ProjectStudent student: students){
            answer = checkStudentInformation(student.getStudent());
            if(answer != null)
                return answer;
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }

    private ServiceAnswer checkStudentInformation(SiptisUser user){
        if(user == null)
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.STUDENT_NOT_FOUND).data(null).build();
        if(user.getFullName() == null || user.getFullName() == "")
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_STUDENT_NAME).data(null).build();
        if(user.getCareer() == null || user.getCareer().isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_USER_CAREER).data(null).build();
        Set<UserCareer> careers = user.getCareer();
        for(UserCareer career: careers){
            if(siptisUserServiceCareerDirectorOperations.getCareerDirectorName(career.getName()) == null)
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_DIRECTOR).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(null).build();
    }

}
