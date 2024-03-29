package backend.siptis.service.project_management.project;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.editors_and_reviewers.ProjectSupervisor;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTeacher;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTutor;
import backend.siptis.model.entity.project_management.Area;
import backend.siptis.model.entity.project_management.Modality;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.project_management.SubArea;
import backend.siptis.model.pjo.dto.project_management.NewProjectDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectStudentRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectSupervisorRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTeacherRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTutorRepository;
import backend.siptis.model.repository.project_management.AreaRepository;
import backend.siptis.model.repository.project_management.ModalityRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.model.repository.project_management.SubAreaRepository;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectServiceCreate {

    private final ProjectRepository projectRepository;
    private final ProjectStudentRepository projectStudentRepository;
    private final ProjectTutorRepository projectTutorRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final ProjectTeacherRepository projectTeacherRepository;
    private final ModalityRepository modalityRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final AreaRepository areaRepository;
    private final SubAreaRepository subAreaRepository;
    private final SemesterInformationRepository semesterInformationRepository;

    public ServiceAnswer createProject(NewProjectDTO dto) {
        if (!semesterInformationRepository.existsSemesterInformationByInProgressIsTrue()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_SEMESTER).build();
        }
        if (projectRepository.existsByName(dto.getName()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NAME_ALREADY_EXIST).build();

        Optional<Modality> optionalModality = modalityRepository.findById(dto.getModalityId());
        if (optionalModality.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.MODALITY_DOES_NOT_EXIST).build();

        Project newProject = new Project();

        ArrayList<ProjectStudent> students = new ArrayList<>();
        for (Long studentId : dto.getStudentsId()) {
            Optional<SiptisUser> userOptional = siptisUserRepository.findById(studentId);
            if (userOptional.isEmpty())
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectStudent projectStudent = new ProjectStudent();
            projectStudent.setStudent(userOptional.get());
            projectStudent.setProject(newProject);
            students.add(projectStudent);
        }

        ArrayList<ProjectTutor> tutors = new ArrayList<>();
        for (Long tutorId : dto.getTutorsId()) {
            Optional<SiptisUser> tutorOptional = siptisUserRepository.findById(tutorId);
            if (tutorOptional.isEmpty())
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectTutor projectTutor = new ProjectTutor();
            SiptisUser user = tutorOptional.get();
            Collection<ProjectTutor> projectsTutor = user.getTutorOf();
            int currentProjects = 0;
            for (ProjectTutor thisTutor : projectsTutor) {
                if (thisTutor.getAccepted() == null || !thisTutor.getAccepted()) {
                    currentProjects++;
                }
            }
            if (currentProjects > 9)
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.CANNOT_ASSIGN_TUTOR).build();
            projectTutor.setTutor(user);
            projectTutor.setProject(newProject);
            tutors.add(projectTutor);
        }

        ArrayList<ProjectSupervisor> supervisors = new ArrayList<>();
        String modalityName = optionalModality.get().getName();
        if (dto.getSupervisorsId() != null &&
                (modalityName.equals(backend.siptis.commons.Modality.TRABAJO_DIRIGIDO.toString())
                        || modalityName.equals(backend.siptis.commons.Modality.ADSCRIPCION.toString()))) {
            for (Long supervisorId : dto.getSupervisorsId()) {
                Optional<SiptisUser> supervisorOptional = siptisUserRepository.findById(supervisorId);
                if (supervisorOptional.isEmpty())
                    return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
                ProjectSupervisor projectSupervisor = new ProjectSupervisor();
                projectSupervisor.setSupervisor(supervisorOptional.get());
                projectSupervisor.setProject(newProject);
                supervisors.add(projectSupervisor);
            }
        } else {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).build();
        }

        ArrayList<ProjectTeacher> teachers = new ArrayList<>();
        for (Long teacherId : dto.getTeachersId()) {
            Optional<SiptisUser> teacherOptional = siptisUserRepository.findById(teacherId);
            if (teacherOptional.isEmpty())
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectTeacher projectTeacher = new ProjectTeacher();
            projectTeacher.setTeacher(teacherOptional.get());
            projectTeacher.setProject(newProject);
            teachers.add(projectTeacher);
        }

        Set<Area> areas = new HashSet<>();
        for (Long areaId : dto.getAreasId()) {
            Optional<Area> areaOptional = areaRepository.findById(areaId);
            if (areaOptional.isEmpty())
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_NOT_FOUND).build();
            Area area = areaOptional.get();
            areas.add(area);
        }

        Set<SubArea> subAreas = new HashSet<>();
        for (Long subAreaId : dto.getSubAreasId()) {
            Optional<SubArea> subAreaOptional = subAreaRepository.findById(subAreaId);
            if (subAreaOptional.isEmpty())
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_NOT_FOUND).build();
            SubArea subArea = subAreaOptional.get();
            subAreas.add(subArea);
        }

        newProject.setName(dto.getName());
        newProject.setModality(modalityRepository.findModalityById(dto.getModalityId()));
        newProject.setStudents(students);
        newProject.setTutors(tutors);
        newProject.setSupervisors(supervisors);
        newProject.setAreas(areas);
        newProject.setSubAreas(subAreas);
        newProject.setPhase("REVIEWERS_PHASE");
        newProject.setTeachers(teachers);
        newProject.setPeriod(semesterInformationRepository.getCurrentPeriod());

        projectRepository.save(newProject);
        projectStudentRepository.saveAll(students);
        projectTutorRepository.saveAll(tutors);
        projectSupervisorRepository.saveAll(supervisors);
        projectTeacherRepository.saveAll(teachers);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUCCESSFUL_PROJECT_REGISTER).data(newProject).build();
    }
}
