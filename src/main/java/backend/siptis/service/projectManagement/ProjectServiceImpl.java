package backend.siptis.service.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.*;
import backend.siptis.model.entity.userData.Schedule;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.model.pjo.vo.projectManagement.*;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.projectManagement.PlaceToDefenseRepository;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.projectManagement.ReviewRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectTribunalRepository projectTribunalRepository;
    private final PlaceToDefenseRepository placeToDefenseRepository;

    @Override
    public ServiceAnswer getProjects(){
        List<Project> proyectos = projectRepository.findAll();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
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
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SIN_PRESENTACIONES).data(null).build();
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
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectCompleteInfo(project)).build();
    }

    @Override
    public ServiceAnswer getProjectInfoToAssignTribunals(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
        if(query.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        Project project = query.get();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectInfoToAssignTribunals(project)).build();
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

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Tribunals has been assigned to the project").build();
    }

    @Override
    public ServiceAnswer getSchedulesInfoToAssignADefense(Long idProject) {
        Optional<Project> query = projectRepository.findById(idProject);
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
        if(project.getDefense() != null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_HAS_ALREADY_A_DEFENSE_DATE).data(null).build();
        }
        Optional<PlaceToDefense> place = placeToDefenseRepository.findById(defenseDTO.getIdPlace());
        if(place.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_PLACE_DOES_NOT_EXIST).data(null).build();
        }
        Defense newDefense =  new Defense(place.get(), project, defenseDTO.getDate());
        project.setDefense(newDefense);
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Defense created").build();
    }

    private UserDefenseScheduleVO createDefenseInfo(SiptisUser student) {
        HashMap<String, List<String[]>> schedules = new HashMap<>();
        for(Schedule schedule:student.getAvailableSchedules()){
            String day = schedule.getDayS();
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
