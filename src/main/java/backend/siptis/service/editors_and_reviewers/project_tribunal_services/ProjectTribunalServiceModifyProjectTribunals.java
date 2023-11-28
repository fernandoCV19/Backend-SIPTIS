package backend.siptis.service.editors_and_reviewers.project_tribunal_services;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.pjo.dto.project_management.AssignTribunalsDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectTribunalServiceModifyProjectTribunals {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;

    public ServiceAnswer removeTribunals(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data("").build();
        }
        Project project = optionalProject.get();
        if (project.getTribunals().isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.TRIBUNALS_ERROR).data("PROJECT DOES NOT HAVE TRIBUNALS TO REMOVE").build();
        }

        if (project.getTotalDefensePoints() != null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.TRIBUNALS_ERROR).data("THIS PROJECT HAS BEEN DEFENDED").build();
        }

        projectTribunalRepository.deleteAll(project.getTribunals());
        project.setPhase(PhaseName.ASSIGN_TRIBUNALS_PHASE.toString());
        projectRepository.save(project);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("TRIBUNALS HAS BEEN REMOVED").build();
    }

    public ServiceAnswer assignTribunals(AssignTribunalsDTO assignTribunalsDTO) {
        List<Long> tribunalsIds = assignTribunalsDTO.getTribunalsIds();
        Long projectId = assignTribunalsDTO.getProjectId();
        List<SiptisUser> tribunals = new ArrayList<>();
        for (Long id : tribunalsIds) {
            Optional<SiptisUser> user = siptisUserRepository.findById(id);
            if (user.isEmpty()) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_ID_DOES_NOT_EXIST).data(id).build();
            }
            if (user.get().getRoles().stream().noneMatch(role -> role.getName().equals("TRIBUNAL"))) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.USER_IS_NOT_A_TRIBUNAL).data(id).build();
            }
            tribunals.add(user.get());
        }
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }

        for (SiptisUser user : tribunals) {
            ProjectTribunal projectTribunal = new ProjectTribunal(user, project.get());
            projectTribunalRepository.save(projectTribunal);
        }

        Project projectNotOptional = project.get();
        projectNotOptional.setPhase(PhaseName.TRIBUNALS_PHASE.toString());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data("Tribunals has been assigned to the project").build();
    }
}
