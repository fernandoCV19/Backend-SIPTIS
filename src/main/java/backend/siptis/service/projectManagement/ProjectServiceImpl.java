package backend.siptis.service.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.*;
import backend.siptis.model.entity.userData.Schedule;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import backend.siptis.model.pjo.vo.projectManagement.*;
import backend.siptis.model.repository.editorsAndReviewers.ModalityRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectStudentRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTutorRepository;
import backend.siptis.model.repository.projectManagement.*;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
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
    private final ModalityRepository modalityRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final PlaceToDefenseRepository placeToDefenseRepository;
    private final DefenseRepository defenseRepository;

    @Override
    public ServiceAnswer createProject(NewProjectDTO dto) {
        if(projectRepository.existsByName(dto.getName()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                    .data("El nombre ya se encuentra registrado").build();
        if(!modalityRepository.existsById(dto.getModalityId()))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                    .data("No se pudo encontrar la modalidad solicitada.").build();

        Project newProject = new Project();
        ArrayList<ProjectStudent> students = new ArrayList<>();

        for (Long studentId: dto.getStudentsId()) {
            if(!siptisUserRepository.existsById(studentId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                        .data("No se pudo encontrar al estudiante solicitado.").build();

            ProjectStudent projectStudent = new ProjectStudent();
            projectStudent.setStudent(siptisUserRepository.findById(studentId).get());
            projectStudent.setProject(newProject);
            students.add(projectStudent);
        }

        ArrayList<ProjectTutor> tutors = new ArrayList<>();
        for (Long tutorId: dto.getTutorsId()) {
            if(!siptisUserRepository.existsById(tutorId))
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR)
                        .data("No se pudo encontrar al usuario solicitado.").build();

            ProjectTutor projectTutor = new ProjectTutor();
            projectTutor.setTutor(siptisUserRepository.findById(tutorId).get());
            projectTutor.setProject(newProject);
            tutors.add(projectTutor);
        }

        newProject.setName(dto.getName());
        newProject.setModality(modalityRepository.findModalityById(dto.getModalityId()));
        newProject.setStudents(students);
        newProject.setTutors(tutors);

        projectRepository.save(newProject);

        for (ProjectStudent projectStudent: students)
            projectStudentRepository.save(projectStudent);

        for (ProjectTutor projectTutor: tutors)
            projectTutorRepository.save(projectTutor);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                    .data("El proyecto fue registrado correctamente.").build();
    }

    @Override
    public ServiceAnswer getProjects(){
        List<Project> proyectos = projectRepository.findAll();
        if (proyectos.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(proyectos).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
    }

    @Override
    public ServiceAnswer getProjectsList(String search, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
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
    public ServiceAnswer getProjectById(Long id){
        Optional<Project> query = projectRepository.findById(id);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(project).build();
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
    public ServiceAnswer assignTribunals(AssignTribunalsDTO assignTribunalsDTO) {
        List<Long> tribunalsIds = assignTribunalsDTO.getTribunalsIds();
        Long projectId = assignTribunalsDTO.getProjectId();
        List<SiptisUser> tribunals = new ArrayList<>();
        for(Long id: tribunalsIds){
            Optional<SiptisUser> user = siptisUserRepository.findById(id);
            if(user.isEmpty()){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(id).build();
            }
            if(user.get().getRoles().stream().noneMatch(role -> role.getName().equals("TRIBUNAL"))){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_IS_NOT_A_TRIBUNAL).data(id).build();
            }
            tribunals.add(user.get());
        }
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        for(SiptisUser user: tribunals){
            ProjectTribunal projectTribunal = new ProjectTribunal(user, project.get());
            projectTribunalRepository.save(projectTribunal);
        }

        Project projectNotOptional = project.get();
        projectNotOptional.setPhase(Phase.DEFENSE_PHASE.toString());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Tribunals has been assigned to the project").build();
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
    public ServiceAnswer addDefense(DefenseDTO defenseDTO) {
        Optional<Project> query = projectRepository.findById(defenseDTO.getIdProject());
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Project project = query.get();
        List<Defense> reservations = defenseRepository.findByplaceToDefenseId(defenseDTO.getIdProject());
        for(Defense defense: reservations){
            long diffMillis = defenseDTO.getDate().getTime() - defense.getDate().getTime() ;
            long diffHours = TimeUnit.HOURS.convert(diffMillis, TimeUnit.MILLISECONDS);
            if(Math.abs(diffHours) <= 1){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_ANOTHER_RESERVATION_TOO_CLOSE).data(defense.getDate()).build();
            }
        }
        if(project.getDefense() != null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_A_DEFENSE_DATE).data(null).build();
        }
        Optional<PlaceToDefense> place = placeToDefenseRepository.findById(defenseDTO.getIdPlace());
        if(place.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_PLACE_DOES_NOT_EXIST).data(null).build();
        }
        Defense newDefense =  new Defense(place.get(), project, defenseDTO.getDate());
        defenseRepository.save(newDefense);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Defense created").build();
    }

    @Override
    public ServiceAnswer getProjectsToDefenseOrDefended(Long userId) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(userId);
        if(userOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(null).build();
        }
        List<Project> projectsToDefend = projectRepository.findByPhaseAndTribunalsTribunalId(Phase.DEFENSE_PHASE.toString(), userId);
        List<Project> projectsDefended = projectRepository.findByPhaseAndTribunalsTribunalId(Phase.POST_DEFENSE_PHASE.toString(), userId);
        InfoToDefensesSectionVO data = new InfoToDefensesSectionVO(projectsToDefend, projectsDefended);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getProjectsWithoutAndWithTribunals() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectCompleteInfoVO> withTribunals = projects.stream().filter(project -> !project.getTribunals().isEmpty()).map(ProjectCompleteInfoVO::new).toList();
        List<ProjectCompleteInfoVO> withoutTribunals = projects.stream().filter(project -> project.getTribunals().isEmpty()).map(ProjectCompleteInfoVO::new).toList();
        ProjectsWithoutAndWithTribunalsVO data = new ProjectsWithoutAndWithTribunalsVO(withTribunals, withoutTribunals);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    @Override
    public ServiceAnswer getProjectsWithoutAndWithDefensePlace() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectCompleteInfoVO> withDefense = projects.stream().filter(project ->  project.getDefense() != null && project.getPhase().equals(Phase.DEFENSE_PHASE.toString())).map(ProjectCompleteInfoVO::new).toList();
        List<ProjectCompleteInfoVO> withoutDefense = projects.stream().filter(project ->  project.getDefense() == null && project.getPhase().equals(Phase.DEFENSE_PHASE.toString())).map(ProjectCompleteInfoVO::new).toList();
        ProjectsWithoutAndWithTribunalsVO data = new ProjectsWithoutAndWithTribunalsVO(withDefense, withoutDefense);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
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

        // Diferencia en milisegundos
        long diffMillis = now.getTime() - compare.getTime() ;

        // Diferencia en días
        long diffDias = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        return (int) diffDias;
    }
}
