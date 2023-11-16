package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceGetPaginatedProjects {

    private final ProjectRepository projectRepository;

    public ServiceAnswer getPaginatedCompletedProjects(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllWithDefense(pageable);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage).build();
    }

    public ServiceAnswer getPaginatedCompletedProjectsByName(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByNameWithDefense(pageable, name);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage).build();
    }

    public ServiceAnswer getPaginatedCompletedProjectsByModality(int pageNumber, int pageSize, String modality) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByModalityWithDefense(pageable, modality);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }

    public ServiceAnswer getPaginatedCompletedProjectsByArea(int pageNumber, int pageSize, String area) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllByAreaWithDefense(pageable, area);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }

    public ServiceAnswer getPaginatedCompletedProjectsBySubArea(int pageNumber, int pageSize, String subArea) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Project> projectPage = projectRepository.findAllBySubAreaWithDefense(pageable, subArea);
        if (projectPage.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_PROJECTS).data(null).build();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(projectPage.getContent()).build();
    }
}
