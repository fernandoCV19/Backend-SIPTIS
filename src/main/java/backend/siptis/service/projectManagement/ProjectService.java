package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectService {

    ServiceAnswer getProjects();

    ServiceAnswer getPaginatedCompletedProjects(int pageNumber, int pageSize);

    ServiceAnswer getPaginatedCompletedProjectsByName(int pageNumber, int pageSize, String name);

    ServiceAnswer getPaginatedCompletedProjectsByModality(int pageNumber, int pageSize, String modality);

    ServiceAnswer getPaginatedCompletedProjectsByArea(int pageNumber, int pageSize, String area);

    ServiceAnswer getPaginatedCompletedProjectsBySubArea(int pageNumber, int pageSize, String subArea);

    ServiceAnswer getPaginatedCompletedProjectsByFilters(int pageNumber, int pageSize, String name, String modality, String area, String subArea);

    ServiceAnswer getPresentations (Long idProyecto);

    ServiceAnswer getProjectInfoToReview(Long idProject, Long idReviewer);

    ServiceAnswer getAllProjectInfo(Long idProject);
}
