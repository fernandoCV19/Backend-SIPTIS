package backend.siptis.service.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.projectManagement.*;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.userData.Schedule;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import backend.siptis.model.pjo.dto.projectManagement.ProjectInformationDTO;
import backend.siptis.model.pjo.vo.projectManagement.*;
import backend.siptis.model.repository.editorsAndReviewers.*;
import backend.siptis.model.repository.projectManagement.*;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectStudentRepository projectStudentRepository;
    private final ProjectTutorRepository projectTutorRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final ProjectTeacherRepository projectTeacherRepository;
    private final ModalityRepository modalityRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final AreaRepository areaRepository;
    private final SubAreaRepository subAreaRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final PlaceToDefenseRepository placeToDefenseRepository;
    private final DefenseRepository defenseRepository;
    private final SemesterInformationRepository semesterInformationRepository;


    @Override
    public ServiceAnswer createProject(NewProjectDTO dto) {
        if(!semesterInformationRepository.existsSemesterInformationByInProgressIsTrue()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_CURRENT_SEMESTER).build();
        }
        if(projectRepository.existsByName(dto.getName()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_NAME_ALREADY_EXIST).build();

        if(!modalityRepository.existsById(dto.getModalityId()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.MODALITY_DOES_NOT_EXIST).build();

        Project newProject = new Project();
        ArrayList<ProjectStudent> students = new ArrayList<>();

        for (Long studentId: dto.getStudentsId()) {
            if(!siptisUserRepository.existsById(studentId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectStudent projectStudent = new ProjectStudent();
            projectStudent.setStudent(siptisUserRepository.findById(studentId).get());
            projectStudent.setProject(newProject);
            students.add(projectStudent);
        }

        ArrayList<ProjectTutor> tutors = new ArrayList<>();
        for (Long tutorId: dto.getTutorsId()) {
            if(!siptisUserRepository.existsById(tutorId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectTutor projectTutor = new ProjectTutor();
            projectTutor.setTutor(siptisUserRepository.findById(tutorId).get());
            projectTutor.setProject(newProject);
            tutors.add(projectTutor);
        }

        ArrayList<ProjectSupervisor> supervisors = new ArrayList<>();
        String modalityName = modalityRepository.findModalityById(dto.getModalityId()).getName();

        if(dto.getSupervisorsId() != null &&
                (modalityName.equals(backend.siptis.commons.Modality.TRABAJO_DIRIGIDO.toString())
                        || modalityName.equals(backend.siptis.commons.Modality.ADSCRIPCION.toString()) )){
            for (Long supervisorId: dto.getSupervisorsId()) {
                if(!siptisUserRepository.existsById(supervisorId))
                    return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
                ProjectSupervisor projectSupervisor = new ProjectSupervisor();
                projectSupervisor.setSupervisor(siptisUserRepository.findById(supervisorId).get());
                projectSupervisor.setProject(newProject);
                supervisors.add(projectSupervisor);
            }
        }else{
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).build();
        }

        ArrayList<ProjectTeacher> teachers = new ArrayList<>();
        for (Long teacherId: dto.getTeachersId()) {
            if(!siptisUserRepository.existsById(teacherId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).build();
            ProjectTeacher projectTeacher = new ProjectTeacher();
            projectTeacher.setTeacher(siptisUserRepository.findById(teacherId).get());
            projectTeacher.setProject(newProject);
            teachers.add(projectTeacher);
        }

        Set<Area> areas = new HashSet<>();
        for (Long areaId: dto.getAreasId()) {
            if(!areaRepository.existsAreaById(areaId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.AREA_NOT_FOUND).build();
            Area area = areaRepository.findById(areaId).get();
            areas.add(area);
        }

        Set<SubArea> subAreas = new HashSet<>();
        for (Long subAreaId: dto.getSubAreasId()) {
            if(!subAreaRepository.existsSubAreaById(subAreaId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUB_AREA_NOT_FOUND).build();
            SubArea subArea = subAreaRepository.findById(subAreaId).get();
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
        newProject.setTotalDefensePoints(0.0);
        newProject.setTeachers(teachers);
        newProject.setPeriod(semesterInformationRepository.getCurrentPeriod());

        projectRepository.save(newProject);

        for (ProjectStudent projectStudent: students)
            projectStudentRepository.save(projectStudent);

        for (ProjectTutor projectTutor: tutors)
            projectTutorRepository.save(projectTutor);

        for (ProjectSupervisor projectSupervisor: supervisors)
            projectSupervisorRepository.save(projectSupervisor);

        for (ProjectTeacher projectTeacher: teachers)
            projectTeacherRepository.save(projectTeacher);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.SUCCESSFUL_PROJECT_REGISTER).data(newProject).build();
    }

    @Override
    public ServiceAnswer getProjects(){
        List<Project> proyectos = projectRepository.findAll();
        if (proyectos.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(proyectos).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
    }

    @Override
    public ServiceAnswer getProjectInfo(Long id) {
        if(!projectRepository.existsById(id))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).build();

        Project project = projectRepository.findById(id).get();
        ProjectInformationDTO dto = new ProjectInformationDTO();
        dto.setProjectName(project.getName());
        dto.setPeriod(project.getPeriod());
        Modality modality = project.getModality();
        if(modality != null){
            dto.setModality(modality.getName());
        }
        State state = project.getState();
        if(state != null){
            dto.setState(state.getName());
        }
        dto.setPhase(project.getPhase());
        dto.setStudents(project.getProjectStudents());
        dto.setTutors(project.getProjectTutors());
        dto.setTeachers(project.getProjectTeachers());
        dto.setTribunals(project.getProjectTribunals());
        dto.setSupervisors(project.getProjectSupervisors());
        dto.setAreas(project.getAreasNames());
        dto.setSubareas(project.getSubAreasNames());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();
    }

    @Override
    public ServiceAnswer getProjectsList(String search, Pageable pageable) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data( projectRepository.searchProject(search, pageable))
                .build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjects(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllWithDefense(pageable);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage).build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjectsByName(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByNameWithDefense(pageable, name);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage).build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjectsByModality(int pageNumber, int pageSize, String modality) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByModalityWithDefense(pageable, modality);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjectsByArea(int pageNumber, int pageSize, String area) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByAreaWithDefense(pageable, area);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjectsBySubArea(int pageNumber, int pageSize, String subArea){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllBySubAreaWithDefense(pageable, subArea);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }

    @Override
    public ServiceAnswer getPaginatedCompletedProjectsByFilters(int pageNumber, int pageSize, String name, String modality, String area, String subArea){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllWithFilters(pageable, name, modality, area, subArea);
        if (projectPage.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();

        List<Project> projects = projectPage.getContent();
        int total = projectPage.getTotalPages();
        Map<String, Object> totalResponse = new HashMap<>();
        totalResponse.put ("totalPages", total);
        totalResponse.put ("page", pageNumber);

        List <Map<String, Object>> foundProjects = new ArrayList<>();

        for (Project p: projects){
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("id",p.getId());

            jsonMap.put("projectName", p.getName());
            jsonMap.put("modality", p.getModality().getName());
            jsonMap.put("perfilPath", p.getPerfilPath());
            jsonMap.put("blueBookPath", p.getBlueBookPath());
            jsonMap.put("projectPath", p.getProjectPath());

            jsonMap.put("areas", p.getAreas().toArray());
            jsonMap.put("subAreas", p.getSubAreas().toArray());

            List<ProjectStudent> projectStudents = p.getStudents().stream().toList();
            List <String> studentsNames = new ArrayList<>();
            for (ProjectStudent ps : projectStudents){
                String names = ps.getStudent().getUserInformation().getNames().trim();
                String lastnames = ps.getStudent().getUserInformation().getLastnames().trim();
                studentsNames.add(names+ " " +lastnames);
            }

            jsonMap.put("students", studentsNames);

            List<ProjectTutor> projectTutors = p.getTutors().stream().toList();
            List <String> tutorsNames = new ArrayList<>();
            for (ProjectTutor ps : projectTutors){
                String names = ps.getTutor().getUserInformation().getNames().trim();
                String lastnames = ps.getTutor().getUserInformation().getLastnames().trim();
                tutorsNames.add(names+ " " +lastnames);
            }
            jsonMap.put("tutors", tutorsNames);
            foundProjects.add(jsonMap);
        }
        totalResponse.put("content", foundProjects);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(totalResponse).build();
    }

    @Override
    public ServiceAnswer getPresentations (Long idProyecto){
        Optional<Project> oProyectoGrado = projectRepository.findById(idProyecto);
        if(oProyectoGrado.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project proyecto = oProyectoGrado.get();
        List<Presentation> presentaciones = proyecto.getPresentations().stream().toList();
        if (presentaciones.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PRESENTATIONS).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }

    @Override
    public ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        if(siptisUserRepository.findById(idReviewer).isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();

        List<Long> reviewerIds = projectRepository.getIdsListFromReviewers(idProject);
        if(!reviewerIds.contains(idReviewer)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT).data(null).build();
        }

        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(idProject);
        if(presentation == null){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, -1, Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(data).build();
        }

        if(Boolean.FALSE.equals(presentation.getReviewed())){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.TRUE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }

        Review lastReview = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(idProject, idReviewer);

        if(lastReview == null){
            ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, getDaysDifference(presentation.getDate()), Boolean.FALSE);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
        }

        Integer numberOfDays = getDaysDifference(lastReview.getDate());
        ProjectToReviewSectionVO data = new ProjectToReviewSectionVO(project, Boolean.FALSE, numberOfDays, Boolean.TRUE);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getAllProjectInfo(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectCompleteInfoVO(project)).build();
    }

    @Override
    public ServiceAnswer getProjectInfoToAssignTribunals(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectInfoToAssignTribunalsVO(project)).build();
    }

    @Override
    public ServiceAnswer getInvolvedPeople(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new InvolvedPeopleVO(project)).build();
    }


    @Override
    public ServiceAnswer getSchedulesInfoToAssignADefense(Long projectId) {
        Optional<Project> query = projectRepository.findById(projectId);
        if(query.isEmpty()){
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

    @Override
    public ServiceAnswer getProjectsToDefenseOrDefended(Long userId) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(userId);
        if(userOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }

        List<Project> projectsToDefend = projectTribunalRepository
                .findByTribunal_IdAndProject_PhaseAndDefensePointsNull(userId, Phase.DEFENSE_PHASE.toString())
                .stream()
                .map(ProjectTribunal::getProject)
                .toList();

        List<Project> projectsDefended = projectTribunalRepository
                .findByTribunal_IdAndProject_PhaseAndDefensePointsNotNull(userId, Phase.DEFENSE_PHASE.toString())
                .stream()
                .map(ProjectTribunal::getProject)
                .toList();

        InfoToDefensesSectionVO data = new InfoToDefensesSectionVO(projectsToDefend, projectsDefended);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getProjectsWithoutAndWithTribunals() {
        List<Project> projects = projectRepository.findAll();

        List<ProjectCompleteInfoVO> withTribunals = projects
                .stream()
                .filter(project -> !project.getTribunals().isEmpty() && project.getTotalDefensePoints() == null)
                .map(ProjectCompleteInfoVO::new).toList();

        List<ProjectCompleteInfoVO> withoutTribunals = projects
                .stream().filter(project -> project.getPhase().equals(Phase.ASSIGN_TRIBUNALS_PHASE.toString()))
                .map(ProjectCompleteInfoVO::new).toList();

        ProjectsWithoutAndWithTribunalsVO data = new ProjectsWithoutAndWithTribunalsVO(withTribunals, withoutTribunals);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getProjectsWithoutAndWithDefensePlace() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectCompleteInfoVO> withDefense = projects
                .stream()
                .filter(project ->  project.getDefense() != null && project.getPhase().equals(Phase.DEFENSE_PHASE.toString()) && project.getTotalDefensePoints() == null)
                .map(ProjectCompleteInfoVO::new)
                .toList();
        List<ProjectCompleteInfoVO> withoutDefense = projects
                .stream()
                .filter(project ->  project.getDefense() == null && project.getPhase().equals(Phase.DEFENSE_PHASE.toString()))
                .map(ProjectCompleteInfoVO::new)
                .toList();
        ProjectsWithoutAndWithoutDefensePlaceVO data = new ProjectsWithoutAndWithoutDefensePlaceVO(withDefense, withoutDefense);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }


    @Override
    public ServiceAnswer getNumberOfProjectsByModalityAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByModalityAndCareer(id)).build();
    }

    @Override
    public ServiceAnswer getNumberOfProjectsByAreaAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsByAreasAndCareer(id)).build();
    }

    @Override
    public ServiceAnswer getNumberOfProjectsBySubAreaAndCareer(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberOfProjectsBySubAreasAndCareer(id)).build();
    }

    @Override
    public ServiceAnswer getNumberProjectsByPeriodAndCareer(Long careerId) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getNumberProjectsByPeriodAndCareer(careerId)).build();
    }

    @Override
    public ServiceAnswer getNumberProjectsByCareer(Long careerId) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectRepository.getProjectsByCareer(careerId)).build();
    }

    private UserDefenseScheduleVO createDefenseInfo(SiptisUser student) {
        HashMap<String, List<String[]>> schedules = new HashMap<>();
        for(Schedule schedule:student.getAvailableSchedules()){
            String day = schedule.getDay();
            String start = schedule.getHourStart();
            String finish = schedule.getHourFinish();
            if(!schedules.containsKey(day)){
                schedules.put(day, new ArrayList<>());
            }
            List<String[]> aux = schedules.get(day);
            aux.add(new String[]{start, finish});
        }
        return new UserDefenseScheduleVO(student.getId(), student.getUserInformation().getNames() + " " + student.getUserInformation().getLastnames(), schedules);
    }

    private Integer getDaysDifference(Date compare){
        Date now = new Date();
        long diffMillis = now.getTime() - compare.getTime() ;
        long diffDias = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
        return (int) diffDias;
    }
}
