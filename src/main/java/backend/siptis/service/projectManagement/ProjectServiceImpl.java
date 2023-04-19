package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Presentation;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

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
        if (presentaciones.size()==0){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.SIN_PRESENTACIONES).data(null).build();
        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(presentaciones).build();
    }
}
