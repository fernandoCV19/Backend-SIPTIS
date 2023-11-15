package backend.siptis.service.projectManagement;

import backend.siptis.commons.PhaseName;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.InfoToReviewAProjectVO;
import backend.siptis.model.pjo.vo.projectManagement.ReviewShortInfoVO;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PresentationServiceImpl implements PresentationService {

    private final CloudManagementService nube;
    private final PresentationRepository presentationRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer createPresentation(Long idProyecto, PhaseName fase) {
        Optional<Presentation> presentacionesNoEntregadas = presentationRepository.findByProjectIdAndReviewed(idProyecto, false);
        if (presentacionesNoEntregadas.isPresent()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PENDING_PRESENTATION).data(null).build();
        }
        Optional<Project> oproyectoGrado = projectRepository.findById(idProyecto);
        if (oproyectoGrado.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project project = oproyectoGrado.get();
        Presentation presentation = new Presentation();

        presentation.setPhase(fase.toString());
        presentation.setProject(project);
        presentationRepository.save(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTATION_CREATED).data(presentation).build();

    }

    @Override
    public ServiceAnswer gradePresentation(Long idPresentacion) {
        Optional<Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = opresentacion.get();
        presentation.setReviewed(true);
        Project proyecto = presentation.getProject();
        String fase = presentation.getPhase();
        if (presentation.getProjectPath() != null)
            proyecto.setProjectPath(presentation.getProjectPath());
        if (presentation.getBlueBookPath() != null)
            proyecto.setBlueBookPath(presentation.getBlueBookPath());
        proyecto.setPhase(fase);
        projectRepository.saveAndFlush(proyecto);
        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTACION_REVISADA).data(presentation).build();
    }

    @Override
    public ServiceAnswer attachFile(Long idPresentacion, MultipartFile file, String path) {
        path = correctFileContext(path);
        if (path.equals("Unknown")) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Optional<Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = opresentacion.get();
        String key = nube.putObject(file, path);
        if (path.equals("Libro-Azul/")) {
            presentation.setBlueBookPath(key);
        }
        if (path.equals("Trabajos-Grado/")) {
            presentation.setProjectPath(key);
        }

        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.CLOUD_OPERATION_COMPLETE).data(key).build();
    }

    @Override
    public ServiceAnswer removeFile(Long idPresentacion, String path) {
        path = correctFileContext(path);
        if (path.equals("Unknown")) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Optional<Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = opresentacion.get();
        String key = "";
        if (path.equals("Libro-Azul/")) {
            key = presentation.getBlueBookPath();
            if (key == null) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_FILE_ATTACHED).data(null).build();
            }
            presentation.setBlueBookPath(null);
        }
        if (path.equals("Trabajos-Grado/")) {
            key = presentation.getProjectPath();
            if (key == null) {
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_FILE_ATTACHED).data(null).build();
            }
            presentation.setProjectPath(null);
        }
        nube.deleteObject(key);
        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.CLOUD_OPERATION_COMPLETE).data(presentation).build();
    }

    @Override
    public ServiceAnswer delete(Long idPresentacion) {
        Optional<Presentation> presentacion = presentationRepository.findById(idPresentacion);
        if (presentacion.isPresent()) {
            String blue = presentacion.get().getBlueBookPath();
            String project = presentacion.get().getProjectPath();
            if (blue != null)
                nube.deleteObject(blue);
            if (project != null)
                nube.deleteObject(project);
            presentationRepository.deleteById(idPresentacion);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTATION_DELETED).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
    }


    @Override
    public ServiceAnswer getReviewsFromLastPresentation(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST).data(null).build();
        }
        Presentation presentation = presentationRepository.findTopByProjectIdOrderByDateDesc(projectId);
        if (presentation == null) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.THERE_IS_NO_PRESENTATION_YET).data(null).build();
        }
        List<ReviewShortInfoVO> reviews = presentation.getReviews().stream().map(ReviewShortInfoVO::new).toList();
        InfoToReviewAProjectVO data = new InfoToReviewAProjectVO(projectOptional.get(), reviews);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(data).build();
    }

    private String correctFileContext(String context) {
        if (context.equals("libro-azul")) {
            return "Libro-Azul/";
        }
        if (context.equals("trabajo-grado")) {
            return "Trabajos-Grado/";
        }
        return "Unknown";
    }
}
