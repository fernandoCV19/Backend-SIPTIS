package backend.siptis.service.editorsAndReviewers.projectTribunalServices;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectTribunalServiceGetProjects {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;

    public ServiceAnswer getAllProjectsNotAcceptedReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(id);

        return getProjects(projectsList);
    }

    public ServiceAnswer getAllProjectsNotAcceptedNotReviewedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(id);

        return getProjects(projectsList);
    }

    public ServiceAnswer getAllProjectsAcceptedWithoutDefensePointsByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(id);

        return getProjects(projectsList);
    }

    public ServiceAnswer getAllProjectsDefendedByTribunalId(Long id) {
        if (siptisUserRepository.findById(id).isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST).data(null).build();
        }

        List<ProjectTribunal> projectsList = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(id);

        return getProjects(projectsList);
    }

    private ServiceAnswer getProjects(List<ProjectTribunal> listaProyectos) {
        if (listaProyectos.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.WITHOUT_PROJECTS).data(listaProyectos).build();
        }

        List<ProjectToTribunalHomePageVO> data = listaProyectos
                .stream()
                .map(aux -> new ProjectToTribunalHomePageVO(aux.getProject(), aux.getDefensePoints(), aux.getAccepted(), aux.getReviewed()))
                .toList();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }
}
