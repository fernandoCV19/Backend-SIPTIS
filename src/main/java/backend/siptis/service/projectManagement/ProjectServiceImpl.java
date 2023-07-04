package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.Review;
import backend.siptis.model.pjo.vo.projectManagement.ProjectCompleteInfo;
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
    private final PresentationRepository presentationRepository;
    private final ReviewRepository reviewRepository;
    private final SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer getProjects(){
        List<Project> proyectos = projectRepository.findAll();
        if (proyectos.isEmpty()) return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(proyectos).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(proyectos).build();
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
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(new ProjectCompleteInfo(project)).build();
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
