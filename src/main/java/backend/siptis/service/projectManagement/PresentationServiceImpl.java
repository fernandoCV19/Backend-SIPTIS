package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.repository.projectManagement.PresentationRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private CloudManagementService nube;

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private ProjectRepository proyectoGradoRepository;

    @Override
    public ServiceAnswer createPresentation (Long idProyecto, String fase){
        Optional <Presentation> presentacionesNoEntregadas = presentationRepository.findTopByProyectoGradoIdAndRevisado(idProyecto, false);
        if (presentacionesNoEntregadas.isPresent()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTACION_PENDIENTE).data(null).build();
        }
        Optional<Project> oproyectoGrado = proyectoGradoRepository.findById(idProyecto);
        if (oproyectoGrado.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project project = oproyectoGrado.get();
        Presentation presentation = new Presentation();

        presentation.setPhase(fase);
        presentation.setProject(project);
        presentationRepository.save(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTACION_CREADA).data(presentation).build();

    }

    @Override
    public ServiceAnswer gradePresentation (Long idPresentacion){
        Optional <Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
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
        proyectoGradoRepository.saveAndFlush(proyecto);
        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTACION_REVISADA).data(presentation).build();
    }

    @Override
    public ServiceAnswer attachFile (Long idPresentacion, MultipartFile file, String path){
        path = correctFileContext(path);
        if (path.equals("Unknown")){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Optional <Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = opresentacion.get();
        String key = nube.putObject(file,path);
        if (path.equals("Libro-Azul/")){
            presentation.setBlueBookPath(key);}
        if (path.equals("Trabajos-Grado/")){
            presentation.setProjectPath(key);}

        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OPERACION_DE_NUBE_COMPLETADA).data(key).build();
    }

    @Override
    public ServiceAnswer removeFile (Long idPresentacion, String path){
        path = correctFileContext(path);
        if (path.equals("Unknown")){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        }
        Optional <Presentation> opresentacion = presentationRepository.findById(idPresentacion);
        if (opresentacion.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = opresentacion.get();
        String key = "";
        if (path.equals("Libro-Azul/")){
            key = presentation.getBlueBookPath();
            if(key == null){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_FILE_ATTACHED).data(null).build();
            }
            presentation.setBlueBookPath(null);
        }
        if (path.equals("Trabajos-Grado/")){
            key = presentation.getProjectPath();
            if(key == null){
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.NO_FILE_ATTACHED).data(null).build();
            }
            presentation.setProjectPath(null);
        }
        nube.deleteObject(key);
        presentationRepository.saveAndFlush(presentation);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OPERACION_DE_NUBE_COMPLETADA).data(presentation).build();
    }

    @Override
    public ServiceAnswer delete (Long idPresentacion){
        Optional <Presentation> presentacion = presentationRepository.findById(idPresentacion);
        if (presentacion.isPresent()){
            String blue = presentacion.get().getBlueBookPath();
            String project = presentacion.get().getProjectPath();
            if (blue!= null)
                nube.deleteObject(blue);
            if (project!=null)
                nube.deleteObject(project);
            presentationRepository.deleteById(idPresentacion);
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.PRESENTACION_ELIMINADA).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
    }

    @Override
    public ServiceAnswer downloadFileFromCloud(String key){
        String response= nube.getObject(key);
        if (response == null){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(response).build();

    }

    private String correctFileContext (String context){
        if (context.equals("libro-azul")){return "Libro-Azul/";}
        if (context.equals("trabajo-grado")){return "Trabajos-Grado/";}
        return "Unknown";
    }
}
